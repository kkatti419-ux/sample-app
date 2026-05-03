package com.kartik.myapplication.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")

    object Setting : Screen("Setting")
    object Dashboard : Screen("Dashboard")

    object Favorites : Screen("favorites")

    /** Full product payload is loaded via [com.kartik.myapplication.domain.repository.ProductRepository]; route carries only ID. */
    object ProductDetail : Screen("product_detail/{product_id}") {
        fun createRoute(productId: Long) = "product_detail/$productId"
    }




}