package com.example.fcm.model

data class MessageDto(
    val to: String?,
    val notification: AppNoti,
)

data class AppNoti(val title: String = "", val body: String = "")