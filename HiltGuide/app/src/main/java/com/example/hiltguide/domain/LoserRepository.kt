package com.example.hiltguide.domain

interface LoserRepository {


    suspend fun getLoserData()
}