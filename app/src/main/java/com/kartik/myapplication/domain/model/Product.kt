package com.kartik.myapplication.domain.model

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val rating: Double
)