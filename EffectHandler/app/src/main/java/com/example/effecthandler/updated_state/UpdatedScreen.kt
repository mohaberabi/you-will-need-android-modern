package com.example.effecthandler.updated_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay

@Composable

fun UpdatedStateScreen(
    onTimeOut: () -> Unit,

    ) {


    val updatedOnTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(true) {
        delay(3000L)
        updatedOnTimeOut()
    }
}