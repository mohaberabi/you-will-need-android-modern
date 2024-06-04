package com.example.fcm.viewmodel

data class NotiState(
    val isTypingToken: Boolean = true,
    val remoteToken: String = "",
    val messageText: String = "test ",
)
