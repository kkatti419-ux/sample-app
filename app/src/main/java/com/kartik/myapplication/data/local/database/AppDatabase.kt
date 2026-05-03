package com.kartik.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kartik.myapplication.data.local.entity.ProductEntity
import com.kartik.myapplication.data.local.dao.ProductDao

@Database(
    entities = [ProductEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}