package com.kartik.myapplication.domain.repository

import com.kartik.myapplication.domain.model.ProductList

interface ProductRepository {
    suspend fun getProducts(): Result<ProductList>
}
