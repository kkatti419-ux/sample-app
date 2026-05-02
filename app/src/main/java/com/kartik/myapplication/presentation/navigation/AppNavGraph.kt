package com.kartik.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kartik.myapplication.presentation.product.ProductViewModel

@Composable
fun AppNavGraph(startViewModel: ProductViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val startDestination by startViewModel.startDestination.collectAsState()
    NavHost(navController = navController, startDestination = "home"){
        simpleNavGraph(navController)
    }


}