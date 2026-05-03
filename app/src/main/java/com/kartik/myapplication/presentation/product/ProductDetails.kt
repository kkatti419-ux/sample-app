package com.kartik.myapplication.presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kartik.myapplication.domain.model.ProductDetail
import com.kartik.myapplication.presentation.profile.InfoText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    onBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val ui by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        when (val state = ui) {
            ProductDetailUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProductDetailUiState.Success -> {
                ProductDetailsBody(
                    product = state.product,
                    modifier = Modifier.padding(innerPadding),
                )
            }
            is ProductDetailUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(state.message, color = MaterialTheme.colorScheme.error)
                        Button(onClick = { viewModel.load() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductDetailsBody(
    product: ProductDetail,
    modifier: Modifier = Modifier,
) {
    val p = product
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            val thumb = p.thumbnail.takeIf { it.isNotBlank() }
            if (thumb != null) {
                AsyncImage(
                    model = thumb,
                    contentDescription = p.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Spacer(Modifier.height(200.dp))
            }
        }

        item {
            InfoText("Title", p.title)
            InfoText("Description", p.description)
            InfoText("Category", p.category)
            InfoText("Price", "₹${p.price}")
            InfoText("Discount", "${p.discountPercentage}%")
            InfoText("Rating", p.rating.toString())
            InfoText("Stock", p.stock.toString())
        }

        item {
            InfoText("Tags", p.tags.joinToString())
        }

        item {
            InfoText("Brand", p.brand)
            InfoText("SKU", p.sku)
            InfoText("Weight", "${p.weight}g")
        }

        item {
            InfoText("Width", p.dimensions.width.toString())
            InfoText("Height", p.dimensions.height.toString())
            InfoText("Depth", p.dimensions.depth.toString())
        }

        item {
            InfoText("Warranty", p.warrantyInformation)
            InfoText("Shipping", p.shippingInformation)
            InfoText("Availability", p.availabilityStatus)
        }

        item {
            InfoText("Return Policy", p.returnPolicy)
            InfoText("Minimum Order Qty", p.minimumOrderQuantity.toString())
        }

        item {
            InfoText("Created At", p.meta.createdAt)
            InfoText("Updated At", p.meta.updatedAt)
            InfoText("Barcode", p.meta.barcode)
        }

        item {
            val qr = p.meta.qrCode.takeIf { it.isNotBlank() }
            if (qr != null) {
                AsyncImage(
                    model = qr,
                    contentDescription = "QR Code",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                )
            }
        }

        items(p.images.filter { it.isNotBlank() }) { image ->
            AsyncImage(
                model = image,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
            )
        }

        item {
            Text("Reviews", style = MaterialTheme.typography.titleMedium)
        }

        items(p.reviews) { review ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(10.dp),
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
