package com.example.gooderrorhandeling.util

import com.example.gooderrorhandeling.R
import com.example.gooderrorhandeling.util.app_error.DataError


fun DataError.asUiText(): UiText {


    return when (this) {


        DataError.NetWork.SERIALIZATION_ERROR -> UiText.StringResource(R.string.error_serilize)
        DataError.NetWork.SERVER_ERROR -> UiText.StringResource(R.string.error_serilize)
        DataError.NetWork.NO_NETWORK -> UiText.StringResource(R.string.error_serilize)
        DataError.NetWork.REQUEST_TIMED_OUT -> UiText.StringResource(R.string.error_serilize)
        DataError.Local.DISK_FULL -> UiText.StringResource(R.string.error_serilize)

        else -> {
            UiText.StringResource(R.string.error_serilize)
        }
    }
}

fun AppResult.Error<*, DataError>.asErrorUiText(): UiText {
    return error.asUiText()
}