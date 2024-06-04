package com.example.mvvmvsmvi.mvi.viewmodel

import com.example.mvvmvsmvi.model.Post


data class MviExampleState(
    val isLoading: Boolean = false,
    val isLiked: Boolean = false,
    val post: Post? = null
)