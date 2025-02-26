package com.example.navigationwithargsguide.examples.preseist_navigation.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


/**
 * use this approach when it's very very crutioal to escape the proceess death [like login or logout sessions ]
 */
@Composable
fun PersistentStorageSample(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "screen1"
    ) {
        composable("screen1") {
            val viewModel = hiltViewModel<PersistentViewModel1>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            Screen1(
                onNavigateToScreen2 = {
                    viewModel.saveSession()
                    navController.navigate("screen2")
                }
            )
        }
        composable(
            route = "screen2"
        ) {
            val viewModel = hiltViewModel<PersistentViewModel2>()

            LaunchedEffect(Unit) {
                println("Session: ${viewModel.session}")
            }

            Screen2()
        }
    }
}

@Composable
private fun Screen1(
    onNavigateToScreen2: () -> Unit
) {
    Button(onClick = {
        onNavigateToScreen2()
    }) {
        Text(text = "Go to next screen")
    }
}

@Composable
private fun Screen2() {
    Text(text = "Hello world")
}