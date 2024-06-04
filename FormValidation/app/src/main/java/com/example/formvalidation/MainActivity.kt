package com.example.formvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.formvalidation.prensetation.screens.AuthScreen
import com.example.formvalidation.prensetation.viewmodel.AuthViewModel
import com.example.formvalidation.prensetation.viewmodel.AuthViewModelFactory
import com.example.formvalidation.ui.theme.FormValidationTheme

class MainActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel =
            ViewModelProvider(this, AuthViewModelFactory())[AuthViewModel::class.java]
        setContent {
            FormValidationTheme {
                AuthScreen(viewModel = authViewModel)
            }
        }
    }
}

