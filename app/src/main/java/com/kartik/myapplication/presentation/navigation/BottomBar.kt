package com.kartik.myapplication.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        Screen.Home,
        Screen.Profile,
        Screen.Dashboard,
        Screen.Setting
    )

    NavigationBar {
        items.forEach { screen ->

            NavigationBarItem(
                selected = false,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    when (screen) {
                        Screen.Dashboard -> Icon(Icons.Default.Person, contentDescription = null)
                        Screen.Home -> Icon(Icons.Default.Home, contentDescription = null)
                        Screen.Profile -> Icon(Icons.Default.Person, contentDescription = null)
                        Screen.Setting -> Icon(Icons.Default.Person, contentDescription = null)
                    }
                },
                label = {
                    Text(screen.route)
                }
            )
        }
    }
}