package com.kartik.myapplication.data.remote.model.product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DimensionsDto(
    val width: Double,
    val height: Double,
    val depth: Double,
)