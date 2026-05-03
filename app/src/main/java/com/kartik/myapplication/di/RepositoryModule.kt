package com.kartik.myapplication.di

import com.kartik.myapplication.data.repository.FavoriteRepositoryImpl
import com.kartik.myapplication.data.repository.ProductRepositoryImpl
import com.kartik.myapplication.domain.repository.FavoriteRepository
import com.kartik.myapplication.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        impl: FavoriteRepositoryImpl
    ): FavoriteRepository
}
