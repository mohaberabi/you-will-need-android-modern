package com.example.retrofitguide.presentation.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.retrofitguide.data.AppGraph
import com.example.retrofitguide.data.repository.ProductRepository
import com.example.retrofitguide.util.AppResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {


    private val _state = MutableStateFlow(ProductState())


    val state: StateFlow<ProductState>
        get() = _state


    init {
        getProducts()
    }

    private fun getProducts() {
        _state.update {
            it.copy(
                status = ProductStatus.LOADING
            )
        }
        viewModelScope.launch {

            when (val productsRes = productRepository.getProducts()) {
                is AppResult.Error -> {
                    _state.update {
                        it.copy(
                            status = ProductStatus.ERROR,
                            error = productsRes.message ?: "ERROR"
                        )
                    }
                }

                is AppResult.Done -> {
                    _state.update {
                        it.copy(
                            products = productsRes.data!!.products,
                            status = ProductStatus.DONE,
                        )
                    }
                }
            }


        }
    }
}

class ProductViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(AppGraph.productRepository) as T
        }
        throw IllegalArgumentException("Error")
    }
}