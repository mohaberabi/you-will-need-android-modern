package com.example.effecthandler.produced_state_example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay


@Composable

fun producedStateExample(countUpTo: Int): State<Int> {


    // it is a type of rx programming like a flows which produces a pipeline  of state
    return produceState(initialValue = 0) {


        while (value < countUpTo) {
            delay(1000L)
            value++
        }
    }
}