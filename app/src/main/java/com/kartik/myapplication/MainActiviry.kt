package com.kartik.myapplication;

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.materia
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelloText()
        }
    }
}

@Composable
fun HelloText() {
    Text("Hello Jetpack Compose 🚀")
}