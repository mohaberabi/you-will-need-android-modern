package com.example.navigationwithargsguide.examples.navigation_viewmodel.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun AccountInfoScreen(
    sharedState: Int,
    onNavigate: () -> Unit
) {
    Button(onClick = onNavigate) {
        Text(text = "Click me")
    }
}

@Composable
fun TermsAndConditionsScreen(
    sharedState: Int,
    onOnboardingFinished: () -> Unit
) {
    Button(onClick = onOnboardingFinished) {
        Text(text = "State: $sharedState")
    }
}