package com.diyalotech.bussewasdk.network.retrofit

import com.diyalotech.bussewasdk.network.dto.ApiResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {
        val result = withContext(Dispatchers.IO) { apiCall.invoke() }
        ApiResult.Success(result)
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> {
                println(throwable.printStackTrace())
                ApiResult.Error("Error while retrieving data from server.")
            }
            is HttpException -> {
                val code = throwable.code()
                println("$code: ${throwable.message()}")
                ApiResult.Error("Couldn't connect with server.")
            }
            else -> {
                throwable.printStackTrace()
                ApiResult.Error("Something went wrong.")
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String {
    return try {
        val error = Gson().fromJson(
            throwable.response()?.errorBody()?.string(),
            GenericHttpErrorDTO::class.java
        )
        error.message
    } catch (exception: Exception) {
        "Something went wrong."
    }
}

private data class GenericHttpErrorDTO(val message: String, val errorCode: Int)