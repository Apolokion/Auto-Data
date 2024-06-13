package com.example.auto_data.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auto_data.R

@Composable
fun NewsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        item {
            NewsItem(
                "Tesla releases new model",
                "The new Tesla Model Z is faster, more efficient, and packed with cutting-edge technology.",
                R.drawable.tesla_news
            )
        }
        item {
            NewsItem(
                "Ford announces electric Mustang",
                "Ford's new Mustang EV promises high performance and zero emissions.",
                R.drawable.mustang_news
            )
        }
        item {
            NewsItem(
                "BMW introduces self-driving feature",
                "BMW's latest software update includes a fully autonomous driving mode.",
                R.drawable.bmw_news
            )
        }
    }
}

@Composable
fun NewsItem(title: String, description: String, imageRes: Int) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = { /* Handle read more action */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Read more",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}
