package com.diyalotech.bussewasdk.network.dto

internal sealed class ApiResult<out T> {
    class Success<T>(val data: T): ApiResult<T>()
    class Error(val error: String = "null"): ApiResult<Nothing>()
}