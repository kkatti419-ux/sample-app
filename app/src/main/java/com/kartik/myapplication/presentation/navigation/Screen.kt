package com.kartik.myapplication.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")

    object Setting : Screen("Setting")
    object Dashboard : Screen("Dashboard")




}