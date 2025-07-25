package com.example.auto_data.ui.screen_wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter


@Composable
fun WishlistScreen(viewModel: WishListScreenViewModel = viewModel()) {
    val wishlist = viewModel.wishlist

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Car Wishlist",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(wishlist) { car ->
                WishlistItem(
                    car = car,
                    onRemove = { viewModel.removeCar(it) }
                )
            }
        }
    }
}

@Composable
fun WishlistItem(car: Car, onRemove: (Car) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = car.imageUrl),
                contentDescription = "${car.brand} ${car.model}",
                modifier = Modifier
                    .width(160.dp)
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(text = car.brand, style = MaterialTheme.typography.bodyLarge)
                Text(text = car.model, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Year: ${car.year}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Engine: ${car.engineSize}", style = MaterialTheme.typography.bodySmall)
                Text(
                    text = "Fuel Type: ${car.fuelType}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Drivetrain: ${car.drivetrain}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = { onRemove(car) }) {
                Icon(Icons.Default.Delete, contentDescription = "Remove from Wishlist")
            }
        }
    }
}
