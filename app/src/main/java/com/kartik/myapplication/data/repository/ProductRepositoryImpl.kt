package com.kartik.myapplication.data.repository

import com.kartik.myapplication.data.local.dao.ProductDao
import com.kartik.myapplication.data.mapper.toDomain
import com.kartik.myapplication.data.mapper.toEntity
import com.kartik.myapplication.data.remote.api.ApiService
import com.kartik.myapplication.domain.model.ProductList
import com.kartik.myapplication.domain.repository.ProductRepository
import javax.inject.Inject

/**
 * Single implementation of [ProductRepository]: remote is source of truth on success;
 * Room holds a cache for offline fallback on network errors.
 */
class ProductRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val productDao: ProductDao,
) : ProductRepository {

    override suspend fun getProducts(): Result<ProductList> {
        return try {
            val response = api.getProducts()
            val domainList = response.toDomain()
            productDao.insertProducts(domainList.products.map { it.toEntity() })
            Result.success(domainList)
        } catch (e: Exception) {
            val cached = runCatching { productDao.getProducts() }.getOrNull().orEmpty()
            if (cached.isEmpty()) {
                Result.failure(e)
            } else {
                val products = cached.map { it.toDomain() }
                Result.success(
                    ProductList(
                        products = products,
                        total = products.size.toLong(),
                        skip = 0,
                        limit = products.size.toLong(),
                    )
                )
            }
        }
    }
}
