package com.kartik.myapplication.data.mapper

import com.kartik.myapplication.data.local.entity.ProductEntity
import com.kartik.myapplication.data.remote.model.product.ProductDto
import com.kartik.myapplication.data.remote.model.product.ProductResponseDto
import com.kartik.myapplication.domain.model.Product
import com.kartik.myapplication.domain.model.ProductDetail
import com.kartik.myapplication.domain.model.ProductDetailDimensions
import com.kartik.myapplication.domain.model.ProductDetailMeta
import com.kartik.myapplication.domain.model.ProductList
import com.kartik.myapplication.domain.model.ProductReview

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

fun ProductDto.toDomainDetail(): ProductDetail =
    ProductDetail(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock.toInt(),
        tags = tags,
        brand = brand.orEmpty(),
        sku = sku,
        weight = weight.toInt(),
        dimensions = ProductDetailDimensions(
            width = dimensions.width,
            height = dimensions.height,
            depth = dimensions.depth,
        ),
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        reviews = reviews.map {
            ProductReview(
                rating = it.rating.toInt(),
                comment = it.comment,
                date = it.date,
                reviewerName = it.reviewerName,
                reviewerEmail = it.reviewerEmail,
            )
        },
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity.toInt(),
        meta = ProductDetailMeta(
            createdAt = meta.createdAt,
            updatedAt = meta.updatedAt,
            barcode = meta.barcode,
            qrCode = meta.qrCode,
        ),
        images = images,
        thumbnail = thumbnail,
    )

fun Product.toMinimalDetail(): ProductDetail =
    ProductDetail(
        id = id,
        title = title,
        description = "",
        category = "",
        price = price,
        discountPercentage = 0.0,
        rating = rating,
        stock = 0,
        tags = emptyList(),
        brand = "",
        sku = "",
        weight = 0,
        dimensions = ProductDetailDimensions(0.0, 0.0, 0.0),
        warrantyInformation = "",
        shippingInformation = "",
        availabilityStatus = "",
        reviews = emptyList(),
        returnPolicy = "",
        minimumOrderQuantity = 0,
        meta = ProductDetailMeta("", "", "", ""),
        images = emptyList(),
        thumbnail = thumbnail,
    )