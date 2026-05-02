package com.kartik.myapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileCard(name: String, age: Int, color: Color) {
    var count by remember { mutableStateOf(0) }
    Text(
        text = count.toString(),
        modifier = Modifier.padding(10.dp), // ✅ removed fillMaxSize
        color = colorResource(id = R.color.teal_700),
        fontSize = 16.sp
    )

    Button(onClick = {}) { }



    }

