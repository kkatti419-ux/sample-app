package com.kartik.myapplication.data.model.product

data class ProductResponseDto(
    val products: List<ProductDto>,
    val total: Long,
    val skip: Long,
    val limit: Long,
)
//Root is a bad name so ProductResponseDto