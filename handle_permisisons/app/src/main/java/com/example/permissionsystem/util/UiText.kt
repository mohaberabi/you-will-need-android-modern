package com.example.permissionsystem.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


sealed class UiText {

    data class DynamicText(val value: String) : UiText()


    class StringRes(
        @androidx.annotation.StringRes val resId: Int,
    ) : UiText()


    @Composable

    fun asString(): String {
        return when (this) {
            is DynamicText -> value
            is StringRes -> stringResource(resId)
        }
    }
}