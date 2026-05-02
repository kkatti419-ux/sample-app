package com.kartik.myapplication.presentation.profile


import com.kartik.myapplication.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

// -------------------- SCREEN --------------------

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Avatar
        ProfileAvatar(
            name = "Kartik Katti",
            imageUrl = null
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Kartik Katti",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text("@kartik")

        Spacer(modifier = Modifier.height(16.dp))

        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionBox("Call", imageRes = R.drawable.img_call)
            ActionBox("Email", imageRes = R.drawable.ic_email)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .weight(1f) // 👈 important
                .verticalScroll(scrollState)

        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // Info Card
            InfoCard("company")
            Spacer(modifier = Modifier.height(16.dp))

            InfoCard("info")
            Spacer(modifier = Modifier.height(16.dp))

            InfoCard("none")
            Spacer(modifier = Modifier.height(16.dp))
        }


    }
}


//# 👤 Avatar with initials fallback

@Composable
fun ProfileAvatar(
    name: String,
    imageUrl: String? = null,
    imageRes: Int? = null
) {
    val initials = name
        .split(" ")
        .take(2)
        .map { it.first() }
        .joinToString("")
        .uppercase()

    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(Color(0xFF6200EE)),
        contentAlignment = Alignment.Center
    ) {

        when {
            // 🌐 Remote image (https)
            imageUrl != null -> {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 📁 Local drawable (png/jpg/svg)
            imageRes != null -> {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 🔤 Fallback initials
            else -> {
                Text(
                    text = initials,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//# 🔘 Action Button Box

@Composable
fun ActionBox(text: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray.copy(alpha = 0.5f))
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp) // better icon size
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text, color = Color.White)
        }
    }
}

//# 📦 Info Card

@Composable
fun InfoCard(cardType:String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        when(cardType){
            "company" -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InfoText("Email", "kartik@gmail.com")
                    InfoText("Phone", "1234567890")
                    InfoText("Website", "kartik.dev")
                    InfoText("company name", "kartik@gmail.com")
                    InfoText("catchPhrase", "1234567890")
                    InfoText("bs", "kartik.dev")
                }
            }
            "info" -> {
                Column() {
                    InfoText("id", "kartik@gmail.com")
                    InfoText("name", "1234567890")
                    InfoText("username", "kartik.dev")
                }
            }
            else -> {
                Column() {
                    InfoText("Street", "kartik@gmail.com")
                    InfoText("suite", "1234567890")
                    InfoText("city", "kartik.dev")
                    InfoText("Zipcode", "kartik@gmail.com")
                    InfoText("lat", "1234567890")
                    InfoText("lng", "kartik.dev")

                }

            }



        }


    }
}




//✨ Rich Text

@Composable
fun InfoText(label: String, value: String) {
    Text(
        buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$label: ")
            }
            withStyle(SpanStyle(color = Color.Black)) {
                append(value)
            }
        }
    )
}