package com.example.retrofitguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.retrofitguide.presentation.screens.ProductsScreen
import com.example.retrofitguide.presentation.viewmodel.ProductViewModel
import com.example.retrofitguide.presentation.viewmodel.ProductViewModelFactory
import com.example.retrofitguide.ui.theme.RetrofitGuideTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ProductViewModel>(factoryProducer = { ProductViewModelFactory() })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RetrofitGuideTheme {

                ProductsScreen(viewModel = viewModel)
            }
        }
    }
}