package com.kartik.myapplication.data.model.product

data class ReviewDto(
    val rating: Long,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String,
)