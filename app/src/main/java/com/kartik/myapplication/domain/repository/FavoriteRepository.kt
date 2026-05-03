package com.kartik.myapplication.domain.repository

import com.kartik.myapplication.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun observeFavoriteIds(): Flow<Set<Long>>

    fun observeFavoriteProducts(): Flow<List<Product>>

    suspend fun toggleFavorite(product: Product)
}
