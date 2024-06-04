package com.example.gooderrorhandeling.util

import com.example.gooderrorhandeling.util.app_error.AppError


typealias RootError = AppError

sealed interface AppResult<out DATA, out ERROR : RootError> {


    data class Done<out DATA, out ERROR : RootError>(val data: DATA) : AppResult<DATA, ERROR>


    data class Error<out DATA, out ERROR : RootError>(val error: ERROR) : AppResult<DATA, ERROR>

}