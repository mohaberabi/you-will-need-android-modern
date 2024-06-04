package com.example.mvvmvsmvi.mvi.viewmodel

import com.example.mvvmvsmvi.model.Post

sealed class MviExampleEvent {


    data object ToggleLikeEvent : MviExampleEvent()

    data class OnPostClickedEvent(val post: Post) : MviExampleEvent()
}