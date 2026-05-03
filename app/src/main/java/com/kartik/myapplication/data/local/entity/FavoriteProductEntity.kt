package com.kartik.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteProductEntity(
    @PrimaryKey val productId: Long,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val rating: Double,
    /** Newest favorites first when sorted */
    val likedAtMillis: Long,
)
