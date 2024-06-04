package com.example.retrofitguide.data.api

import com.example.retrofitguide.data.model.ProductResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductApi {

    companion object {
        const val BASE_URL = "https://dummyjson.com/"
    }

    @GET("products/")
    suspend fun getProducts(
    ): ProductResponse
}


