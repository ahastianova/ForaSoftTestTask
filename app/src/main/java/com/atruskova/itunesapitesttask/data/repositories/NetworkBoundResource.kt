package com.atruskova.itunesapitesttask.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.atruskova.itunesapitesttask.AppExecutors
import com.atruskova.itunesapitesttask.data.*
import retrofit2.Call

abstract class NetworkBoundResource <RequestType, ResultType> (private val executors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        try {
            result.value = Resource.loading(null)
            val dbSource = loadFromDb()
            result.addSource(dbSource) { data ->
                result.removeSource(dbSource)
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource)
                } else {
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
            }
        } catch (e: Exception) {
            result.value = Resource.error(null, e.message.toString())
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        executors.networkIO().execute {
            try {
                var response = ApiResponse.create(apiResponse.execute())
                when (response) {
                    is ApiSuccessResponse -> {
                        executors.diskIO().execute {
                            saveCallResult(processResponse(response))
                            executors.mainThread().execute {
                                result.removeSource(dbSource)
                                // we specially request a new live data,
                                // otherwise we will get immediately last cached value,
                                // which may not be updated with latest results received from network.
                                result.addSource(loadFromDb()) { newData ->
                                    setValue(
                                        Resource.success(
                                            newData
                                        )
                                    )
                                }
                            }
                        }
                    }
                    is ApiEmptyResponse -> {
                        executors.mainThread().execute {
                            // reload from disk whatever we had
                            result.addSource(loadFromDb()) { newData ->
                                setValue(
                                    Resource.success(
                                        newData
                                    )
                                )
                            }
                        }
                    }
                    is ApiErrorResponse -> {
                        executors.mainThread().execute {
                            onFetchFailed()
                            result.addSource(dbSource) { newData ->
                                setValue(
                                    Resource.error(
                                        newData,
                                        response.errorMessage
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                executors.mainThread().execute {
                    // reload from disk whatever we had
                    result.addSource(loadFromDb()) { newData ->
                        setValue(
                            Resource.error(
                                newData,
                                e.message.toString()
                            )
                        )
                    }
                }
            }
        }

    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun createCall(): Call<RequestType>

}