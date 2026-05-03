package com.kartik.myapplication.data.repository

import com.kartik.myapplication.data.local.dao.FavoriteDao
import com.kartik.myapplication.data.local.entity.FavoriteProductEntity
import com.kartik.myapplication.domain.model.Product
import com.kartik.myapplication.domain.repository.FavoriteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
) : FavoriteRepository {

    override fun observeFavoriteIds(): Flow<Set<Long>> =
        favoriteDao.observeFavoriteIds().map { it.toSet() }

    override fun observeFavoriteProducts(): Flow<List<Product>> =
        favoriteDao.observeFavoriteProducts().map { entities ->
            entities.map { entity ->
                Product(
                    id = entity.productId,
                    title = entity.title,
                    price = entity.price,
                    thumbnail = entity.thumbnail,
                    rating = entity.rating,
                )
            }
        }

    override suspend fun toggleFavorite(product: Product) {
        if (favoriteDao.getFavorite(product.id) != null) {
            favoriteDao.deleteByProductId(product.id)
        } else {
            favoriteDao.insert(
                FavoriteProductEntity(
                    productId = product.id,
                    title = product.title,
                    price = product.price,
                    thumbnail = product.thumbnail,
                    rating = product.rating,
                    likedAtMillis = System.currentTimeMillis(),
                )
            )
        }
    }
}
