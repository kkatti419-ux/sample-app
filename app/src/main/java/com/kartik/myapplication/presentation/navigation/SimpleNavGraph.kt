package com.kartik.myapplication.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.simpleNavGraph(navController: NavController){
    composable(route=Screen.Home.route) {  }
}