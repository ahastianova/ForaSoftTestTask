package com.atruskova.itunesapitesttask.data

import retrofit2.Response

sealed class ApiResponse <T> {
    companion object {
        fun <T> create(errorMessage: Throwable) : ApiErrorResponse<T> {
            return ApiErrorResponse(errorMessage.message.toString())
        }

        fun <T> create(response: Response<T>) : ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body==null || response.code()==204)
                    ApiEmptyResponse()
                else {
                    ApiSuccessResponse(body)
                }
            } else {
                val errorMsg = response.errorBody()?.string()
                ApiErrorResponse(errorMsg?: "unknownError")
            }
        }
    }
}
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse <T> (val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
