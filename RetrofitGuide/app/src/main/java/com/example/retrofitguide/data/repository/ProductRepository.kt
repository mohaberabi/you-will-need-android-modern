package com.example.retrofitguide.data.repository

import com.example.retrofitguide.data.api.ProductApi
import com.example.retrofitguide.data.model.ProductResponse
import com.example.retrofitguide.util.AppResult
import retrofit2.HttpException
import java.io.IOException

interface ProductRepository {
    suspend fun getProducts(): AppResult<ProductResponse>
}


class ProductRepositoryImpl(
    private val productApi: ProductApi
) : ProductRepository {
    override suspend fun getProducts(): AppResult<ProductResponse> {


        return try {
            val products = productApi.getProducts()
            AppResult.Done(products)
        } catch (e: IOException) {
            e.printStackTrace()
            AppResult.Error("Error getting products IO-Exception ${e}")
        } catch (e: HttpException) {
            e.printStackTrace()
            AppResult.Error("Error getting products HTTP-Exception ${e}")
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error("Error getting products Unknown Error ${e}")
        }
    }

}