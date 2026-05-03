package com.kartik.myapplication.data.mapper

import com.kartik.myapplication.data.local.entity.ProductEntity
import com.kartik.myapplication.data.remote.model.product.ProductDto
import com.kartik.myapplication.data.remote.model.product.ProductResponseDto
import com.kartik.myapplication.domain.model.Product
import com.kartik.myapplication.domain.model.ProductList

// data/mapper/ProductMapper.kt
fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        rating = rating
    )
}

fun ProductResponseDto.toDomain(): ProductList {
    return ProductList(
        products = products.map { it.toDomain() },
        total = total,
        skip = skip,
        limit = limit
    )
}

fun Product.toEntity(): ProductEntity =
    ProductEntity(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        rating = rating
    )

fun ProductEntity.toDomain(): Product =
    Product(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        rating = rating
    )