package com.example.navigationwithargsguide.examples.navigation_viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel : ViewModel() {


    private val _state = MutableStateFlow(0)


    val state: StateFlow<Int>
        get() = _state

    fun updateState() {
        _state.update {
            it + 1
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("Viewmodel Cleared")
    }
}