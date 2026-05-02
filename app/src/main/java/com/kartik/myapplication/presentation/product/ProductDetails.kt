package com.kartik.myapplication.presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kartik.myapplication.presentation.profile.InfoText

@Composable
fun ProductDetails(product: Product? = null) {
    val data = product ?: Product(
        id = 1,
        title = "Demo Product",
        description = "Demo description",
        category = "Demo",
        price = 0.0,
        discountPercentage = 0.0,
        rating = 0.0,
        stock = 0,
        tags = emptyList(),
        brand = "Demo",
        sku = "N/A",
        weight = 0,
        dimensions = Dimensions(0.0, 0.0, 0.0),
        warrantyInformation = "N/A",
        shippingInformation = "N/A",
        availabilityStatus = "N/A",
        reviews = emptyList(),
        returnPolicy = "N/A",
        minimumOrderQuantity = 0,
        meta = Meta("", "", "", ""),
        images = emptyList(),
        thumbnail = ""
    )


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // 🔹 Image
        item {
            AsyncImage(
                model = product?.thumbnail,
                contentDescription = product?.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        // 🔹 Basic Info
        item {
            InfoText("Title", product?.title ?: "")
            InfoText("Description", product?.description ?: "")
            InfoText("Category", product?.category ?: "")
            InfoText("Price", "₹${product?.price}")
            InfoText("Discount", "${product?.discountPercentage}%")
            InfoText("Rating", product?.rating.toString())
            InfoText("Stock", product?.stock.toString())
        }

        // 🔹 Tags
        item {
            InfoText("Tags", product?.tags?.joinToString() ?: "")
        }

        // 🔹 Brand & SKU
        item {
            InfoText("Brand", product?.brand ?:"" )
            InfoText("SKU", product?.sku ?: "")
            InfoText("Weight", "${product?.weight}g")
        }

        // 🔹 Dimensions
        item {
            InfoText("Width", product?.dimensions?.width.toString())
            InfoText("Height", product?.dimensions?.height.toString())
            InfoText("Depth", product?.dimensions?.depth.toString())
        }

        // 🔹 Shipping & Warranty
        item {
            InfoText("Warranty", product?.warrantyInformation ?:"" )
            InfoText("Shipping", product?.shippingInformation ?: "")
            InfoText("Availability", product?.availabilityStatus ?: "")
        }

        // 🔹 Policies
        item {
            InfoText("Return Policy", product?.returnPolicy ?:"" )
            InfoText("Minimum Order Qty", product?.minimumOrderQuantity.toString())
        }

        // 🔹 Meta
        item {
            InfoText("Created At", product?.meta?.createdAt ?: "")
            InfoText("Updated At", product?.meta?.updatedAt ?: "")
            InfoText("Barcode", product?.meta?.barcode ?: "")
        }

        // 🔹 QR Code
        item {
            AsyncImage(
                model = product?.meta?.qrCode,
                contentDescription = "QR Code",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }

        // 🔹 Images list
        items(product?.images ?: emptyList()) { image ->
            AsyncImage(
                model = image,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
        }

        // 🔹 Reviews
        item {
            Text("Reviews", style = MaterialTheme.typography.titleMedium)
        }

        items(product?.reviews ?: emptyList()) { review ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(10.dp)
            ) {
                InfoText("Rating", review.rating.toString())
                InfoText("Comment", review.comment)
                InfoText("Date", review.date)
                InfoText("Reviewer", review.reviewerName)
                InfoText("Email", review.reviewerEmail)
            }
        }
    }
}

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String,
    val sku: String,
    val weight: Int,
    val dimensions: Dimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Review>,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val meta: Meta,
    val images: List<String>,
    val thumbnail: String
)

data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double
)

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)