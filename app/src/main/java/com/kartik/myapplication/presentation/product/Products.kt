package com.kartik.myapplication.presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.kartik.myapplication.presentation.profile.InfoText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kartik.myapplication.domain.model.Product
import com.kartik.myapplication.presentation.navigation.Screen

@Composable
fun Products(navController: NavController,viewModel: ProductViewModel= hiltViewModel()) {
    val baseList = listOf(
        "id" to "1",
        "name" to "Kartik",
        "username" to "kartik.dev",
        "email" to "kartik@gmail.com"
    )

    val itemsList = List(100) {
        baseList
    }.flatten()


    val uiState = viewModel.uiState.collectAsState().value
    val favoriteIds by viewModel.favoriteIds.collectAsState()

    when (uiState) {
        is ProductUiState.Idle -> {
            // Call API once
            viewModel.getAllProducts()
        }

        is ProductUiState.Loading -> {
            Text("Loading...")
        }

        is ProductUiState.Success -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),   // ✅ space around grid
                verticalArrangement = Arrangement.spacedBy(12.dp),   // ✅ vertical gap
                horizontalArrangement = Arrangement.spacedBy(12.dp)  // ✅ horizontal gap

            ) {
                items(uiState.data.products, key = { it.id }) { item ->
                    ProductCard(
                        product = item,
                        isFavorite = favoriteIds.contains(item.id),
                        onFavoriteClick = { viewModel.toggleFavorite(item) },
                        onClick = {
                            navController.navigate(Screen.ProductDetail.createRoute(item.id))
                        },
                    )
                }
            }
//            ProductGrid(products = uiState.data.products,navController)
        }

        is ProductUiState.Error -> {
            Text(text = uiState.message, color = Color.Red)
        }
    }


}


//@Composable
//fun ProductCard(modifier: Modifier = Modifier,product: Product) {
//
//    Box(modifier.padding(5.dp).fillMaxWidth().background(color = Color.Gray)) {
//        Column {
//            AsyncImage(
//                model = null,
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//            Image(
//                painter = painterResource(id = R.drawable.ic_email),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
////            Image(painterResource(R.drawable.ic_launcher_background,contentDescription = null),contentScale = ContentScale.Crop)
//            InfoText("title", product.title)
////            InfoText("Description", "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.")
//            InfoText("Category", "Beauty")
//            InfoText("email", "kartik@gmail.com")
//            InfoText("Price", "Pickle")
////            InfoText("Description", "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.")
//            InfoText("Category", "Beauty")
//            InfoText("email", "kartik@gmail.com")
//
//        }
//    }
//}


@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit,
) {
    Box(
        Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(Color.LightGray),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
            )

            InfoText("Title", product.title)
            InfoText("Price", "₹${product.price}")
            InfoText("Rating", product.rating.toString())
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp),
            onClick = onFavoriteClick,
        ) {
            val heartIcon: ImageVector =
                if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
            Icon(imageVector = heartIcon, contentDescription = "Favorite", tint = Color.Red)
        }
    }
}