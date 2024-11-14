package com.example.listofproductes.retrofit

import org.json.JSONObject
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            var message = "Something went wrong!"
            response.errorBody()?.let {
                val data = JSONObject(it.string())
                if (data.has("error_msg")) {

                    if (data.get("error_msg") is JSONObject){
                        val json = JSONObject(data.getString("error_msg"))
                        if (json.has("message")) {
                            message = json.getString("message")
                        }
                    }else if (data.get("error_msg") is String){
                        message = data.getString("error_msg")
                    }

                }
            }
            return error(message)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(errorMessage)
}