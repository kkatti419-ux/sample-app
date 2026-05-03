package com.kartik.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kartik.myapplication.data.local.entity.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteProductEntity)

    @Query("DELETE FROM favorite_products WHERE productId = :productId")
    suspend fun deleteByProductId(productId: Long)

    @Query("SELECT * FROM favorite_products WHERE productId = :productId LIMIT 1")
    suspend fun getFavorite(productId: Long): FavoriteProductEntity?

    @Query("SELECT productId FROM favorite_products")
    fun observeFavoriteIds(): Flow<List<Long>>

    @Query("SELECT * FROM favorite_products ORDER BY likedAtMillis DESC")
    fun observeFavoriteProducts(): Flow<List<FavoriteProductEntity>>
}
