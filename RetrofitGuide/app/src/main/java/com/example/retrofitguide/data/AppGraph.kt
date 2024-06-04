package com.example.retrofitguide.data

import com.example.retrofitguide.data.api.ProductApi
import com.example.retrofitguide.data.repository.ProductRepository
import com.example.retrofitguide.data.repository.ProductRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppGraph {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val productApi: ProductApi = Retrofit.Builder()
        .baseUrl(ProductApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ProductApi::class.java)


    val productRepository: ProductRepository = ProductRepositoryImpl(productApi)
}