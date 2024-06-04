package com.example.effecthandler.launched_effect_example.launched_effect_with_viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collect

@Composable

fun LaunchedEffectViewModelScreen(

    viewModel: LaunchedEffectViewModel
) {

    val snackbarHostState = remember { SnackbarHostState() }

    // a const value was passed to it that means it will only be fired once
    LaunchedEffect(key1 = true) {
        viewModel.state.collect {

                event ->
            when (event) {
                is LaunchedEffectEvents.ShowSnackBar -> {

                    snackbarHostState.showSnackbar(event.message)
                }

                is LaunchedEffectEvents.Navigate -> {

                }
            }
        }


    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    ) {

        Column(modifier = Modifier.padding(it)) {

            Button(onClick = { viewModel.showSnackbar() }) {
                Text(text = "Show snackbar")
            }

        }

    }
}