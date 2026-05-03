package com.kartik.myapplication.data.remote.api

import com.kartik.myapplication.data.remote.model.product.ProductResponseDto
import retrofit2.http.GET

interface ApiService{
    @GET("products")
    suspend fun getProducts(): ProductResponseDto
}