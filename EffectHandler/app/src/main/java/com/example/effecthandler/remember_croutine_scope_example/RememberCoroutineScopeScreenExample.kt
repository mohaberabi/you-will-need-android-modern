package com.example.effecthandler.remember_croutine_scope_example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch


// the remmeeber coroutine scope is a composition aware scope
// where if the compose function in the stack call frame now left the composition whre it was defiend in
// it will be canceleed automatically to avoid any memeory leaks
// that also can be called inside of a composable function to make any async operation safely
@Composable
fun RememberCoroutineScopeExample() {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Send, contentDescription = "") },
                onClick = {
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = "Snackbar",
                                actionLabel = "Action",
                            )
//                        when (result) {
//                            SnackbarResult.ActionPerformed -> {
//                                /* Handle snackbar action performed */
//                            }
//
//                            SnackbarResult.Dismissed -> {
//                                /* Handle snackbar dismissed */
//                            }
//                        }
                    }
                }
            )
        }
    ) { contentPadding ->

        Column(modifier = Modifier.padding(contentPadding)) {

        }

    }
}