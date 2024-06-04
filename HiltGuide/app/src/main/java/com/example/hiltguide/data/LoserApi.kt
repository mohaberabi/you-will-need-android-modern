package com.example.hiltguide.data

import retrofit2.http.GET

interface LoserApi {


    @GET("/loser")
    suspend fun getLoserData()
}