package com.example.navigationwithargsguide.examples.navigation_args

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable

fun ScreenTwo(
    name: String
) {

    Column {

        Text(text = name)
    }
}