package com.example.gooderrorhandeling.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicText(val value: String) : UiText()


    class StringResource(@StringRes val id: Int, val args: Array<Any> = arrayOf()) : UiText() {


    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicText -> value
            is StringResource -> LocalContext.current.getString(id, *args)

        }
    }
}