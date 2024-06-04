package com.example.gooderrorhandeling.util.app_error

sealed interface DataError : AppError {

    enum class NetWork : DataError {
        REQUEST_TIMED_OUT,
        TOO_MANY_REQUESTS,
        NO_NETWORK,
        SERVER_ERROR,
        SERIALIZATION_ERROR,
        UNKNOWN_ERROR
    }

    enum class Local : DataError {

        DISK_FULL,
        
    }
}