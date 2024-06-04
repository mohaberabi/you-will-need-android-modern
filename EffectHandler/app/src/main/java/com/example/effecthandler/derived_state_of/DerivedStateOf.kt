package com.example.effecthandler.derived_state_of

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable

fun DerivedStateOfScreen() {
    var counter by remember {

        mutableStateOf(0)
    }


    // each time the button is pressed the countertext is sadly reassigned in the memeory with the new value
    // in strings  it's not that easy like you see the strings to be assigned in the memorey espically as strings are
    // IMMUTABLES ,  so this is an extra overhead which can be optimized
    val counterText = "THE COUNTER IS ${counter}"

    Button(onClick = { counter++ }) {

        Text(text = counterText)
    }


    // it is uses the concept of caching instead of reassignging the new values to the new created string
    val optimizedCounterText by remember {

        derivedStateOf {
            "THE COUNTER IS ${counter}"
        }
    }
}