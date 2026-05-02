package com.kartik.myapplication.data.api

import com.kartik.myapplication.data.model.product.ProductResponseDto
import retrofit2.http.GET

interface ApiService{
    @GET("products")
    suspend fun getProducts(): ProductResponseDto
}