package com.example.retrofitguide.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.retrofitguide.presentation.compose.ProductCard
import com.example.retrofitguide.presentation.viewmodel.ProductStatus
import com.example.retrofitguide.presentation.viewmodel.ProductViewModel


@Composable
fun ProductsScreen(
    viewModel: ProductViewModel

) {
    val state = viewModel.state.collectAsState()



    when (state.value.status) {
        ProductStatus.LOADING -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        ProductStatus.ERROR -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = state.value.error)
            }
        }

        ProductStatus.DONE -> {
            LazyColumn {
                items(state.value.products) {
                    ProductCard(product = it)
                }
            }
        }

        else -> Unit
    }


}