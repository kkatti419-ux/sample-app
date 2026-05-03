package com.kartik.myapplication.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kartik.myapplication.presentation.product.ProductDetailScreen
import com.kartik.myapplication.presentation.product.Products
import com.kartik.myapplication.presentation.profile.ProfileScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomBarRoutes =
        listOf(Screen.Home.route, Screen.Profile.route, Screen.Dashboard.route, Screen.Setting.route)
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController)
            }
        },
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Screen.Dashboard.route) {
                Products(navController)
            }
            composable(Screen.Setting.route) {
                Text("Profile Screen")
            }
            composable(Screen.Home.route) {
                Text("Home")
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.ProductDetail.route,
                arguments = listOf(
                    navArgument("product_id") { type = NavType.LongType },
                ),
            ) {
                ProductDetailScreen(onBack = { navController.navigateUp() })
            }
        }
    }
}