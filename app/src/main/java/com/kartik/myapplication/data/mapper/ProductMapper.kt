package com.kartik.myapplication.data.mapper

import com.kartik.myapplication.data.model.product.ProductDto
import com.kartik.myapplication.data.model.product.ProductResponseDto
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