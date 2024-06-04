package com.example.mvvmvsmvi.mvi.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmvsmvi.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MviExmapleViewModel : ViewModel() {


    private val _state = MutableStateFlow(MviExampleState())


    fun toggleLike() {
        _state.update {
            it.copy(isLiked = !it.isLiked)
        }
    }

    fun onPostClicked(post: Post) {
        _state.update {
            it.copy(post = post)
        }
    }

    val state: StateFlow<MviExampleState>
        get() = _state

    fun onEvent(event: MviExampleEvent) {
        when (event) {
            is MviExampleEvent.ToggleLikeEvent -> toggleLike()
            is MviExampleEvent.OnPostClickedEvent -> onPostClicked(event.post)
        }
    }
}