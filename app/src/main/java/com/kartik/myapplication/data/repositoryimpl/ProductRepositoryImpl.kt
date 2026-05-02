package com.kartik.myapplication.data.repositoryimpl

import com.kartik.myapplication.data.api.ApiService
import com.kartik.myapplication.data.mapper.toDomain
import com.kartik.myapplication.data.model.product.ProductResponseDto
import com.kartik.myapplication.domain.repository.ProductRepository
import javax.inject.Inject
import com.kartik.myapplication.domain.model.ProductList


// data/repository/ProductRepositoryImpl.kt
class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductList> {
        return try {
            val response = api.getProducts()

            val mapped = response.toDomain() // 🔥 mapping happens here

            Result.success(mapped)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}