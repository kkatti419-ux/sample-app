package com.kartik.myapplication.domain.model

data class ProductList(
    val products: List<Product>,
    val total: Long,
    val skip: Long,
    val limit: Long
)