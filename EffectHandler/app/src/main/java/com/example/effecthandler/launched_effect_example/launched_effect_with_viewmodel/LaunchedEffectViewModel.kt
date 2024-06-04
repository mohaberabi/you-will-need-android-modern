package com.example.effecthandler.launched_effect_example.launched_effect_with_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LaunchedEffectViewModel : ViewModel() {


    private val _state = MutableSharedFlow<LaunchedEffectEvents>()

    val state: SharedFlow<LaunchedEffectEvents>
        get() = _state


    fun showSnackbar() {
        viewModelScope.launch {
            _state.emit(LaunchedEffectEvents.ShowSnackBar("Welcome to the app made by the most dummy app developer in the world "))
        }
    }

}


sealed class LaunchedEffectEvents {

    data class ShowSnackBar(val message: String) : LaunchedEffectEvents()
    data class Navigate(val route: String) : LaunchedEffectEvents()
}