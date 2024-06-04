package com.example.effecthandler.snapshot_flow

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


// this is opposite of collecting the state into composable [myState.collectAsState() , ]
//then you consume  the emitted values into the composable of yours


@Composable

fun SnapshotFlowExample() {

    val state = produceState(initialValue = 0) {


        while (value < 100) {
            delay(1000L)
            value++
        }
    }

    LaunchedEffect(state) {
        snapshotFlow {
            state
        }.collect { value ->
            println(value.value.toString())
        }
    }
}