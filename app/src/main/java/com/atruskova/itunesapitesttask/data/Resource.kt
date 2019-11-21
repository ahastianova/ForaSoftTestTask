package com.atruskova.itunesapitesttask.data

class Resource<out T> (val status: Status, val data: T?, val errorMessage: String?){
    companion object {
        fun <T> loading(data: T?) : Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> success(data: T?) : Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(data: T?, errorMessage: String) : Resource<T> {
            return Resource(Status.ERROR, data, errorMessage )
        }
    }
}