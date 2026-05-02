package com.kartik.myapplication.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kartik.myapplication.presentation.product.ProductDetails
import com.kartik.myapplication.presentation.product.Products
import com.kartik.myapplication.presentation.profile.ProfileScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Dashboard.route) {
                Products(navController)
            }
            composable(Screen.Setting.route) {
                Text("Profile Screen")
            }
            composable(Screen.Home.route) {
                ProductDetails()
            }

            composable(Screen.Profile.route) {
//                Text("Profile Screen") .Profile()
                ProfileScreen()
            }
        }
    }
}