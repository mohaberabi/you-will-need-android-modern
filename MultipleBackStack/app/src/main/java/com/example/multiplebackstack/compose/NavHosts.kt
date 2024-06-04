package com.example.multiplebackstack.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable

fun HomeNavGraph(

) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home1",
    ) {

        for (i in 1..10) {
            composable("home$i") {

                NavScreen(text = "home${i}") {
                    if (i < 10) {
                        navController.navigate("home${i + 1}")
                    }
                }
            }
        }
    }
}


@Composable

fun ChatNavGraph(

) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "chat1",
    ) {

        for (i in 1..10) {
            composable("chat$i") {

                NavScreen(text = "chat${i}") {
                    if (i < 10) {
                        navController.navigate("chat${i + 1}")
                    }
                }
            }
        }
    }
}

@Composable

fun SettingsNavHost(

) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "settings1",
    ) {

        for (i in 1..10) {
            composable("settings$i") {

                NavScreen(text = "settings${i}") {
                    if (i < 10) {
                        navController.navigate("settings${i + 1}")
                    }
                }
            }
        }
    }
}

@Composable
fun NavScreen(
    text: String,
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = text, style = MaterialTheme.typography.titleLarge)
        Button(onClick = {
            onNext()
        }) {
            Text(text = "Next")
        }
    }
}