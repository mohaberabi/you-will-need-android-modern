package com.example.retrofitguide.presentation.viewmodel

import com.example.retrofitguide.data.model.ProductX

enum class ProductStatus {
    INITIAL, LOADING, ERROR, DONE
}

data class ProductState(
    val products: List<ProductX> = emptyList(),
    val error: String = "",
    val status: ProductStatus = ProductStatus.INITIAL,
)
