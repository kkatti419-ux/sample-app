package com.kartik.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kartik.myapplication.data.local.dao.FavoriteDao
import com.kartik.myapplication.data.local.dao.ProductDao
import com.kartik.myapplication.data.local.entity.FavoriteProductEntity
import com.kartik.myapplication.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, FavoriteProductEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun favoriteDao(): FavoriteDao
}