package com.example.fcm.data.remote

import com.example.fcm.model.MessageDto
import retrofit2.http.Body
import retrofit2.http.POST


interface FcmApi {


    @POST("/send")
    suspend fun sendMessage(@Body body: MessageDto)

    @POST("/broadcast")
    suspend fun broadcast(@Body body: MessageDto)
}