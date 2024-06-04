package com.example.mvvmvsmvi.model

data class Post(
    val id: String,
    val user: User,
    val created: String,
    val likes: Int,
)
