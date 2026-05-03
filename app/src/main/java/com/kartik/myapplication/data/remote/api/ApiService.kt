package com.kartik.myapplication.data.remote.api

import com.kartik.myapplication.data.remote.model.product.ProductDto
import com.kartik.myapplication.data.remote.model.product.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getProducts(): ProductResponseDto

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Long): ProductDto
}