package com.example.retrofitguide.util

sealed class AppResult<T>(
    val data: T? = null, val message:
    String? = null
) {

    class Done<T>(data: T) : AppResult<T>(data = data)
    class Error<T>(message: String, data: T? = null) :
        AppResult<T>(message = message, data = data)


}