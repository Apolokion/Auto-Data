package com.example.auto_data.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarModelsScreen(carCompany: String?) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Car Models for $carCompany") })
        }
    ) {
        // Replace with your actual car models list for the selected car company
        val carModels = listOf("Model 1", "Model 2", "Model 3")

        LazyColumn(modifier = Modifier.padding(it)) {
            items(carModels) { model ->
                Text(
                    text = model,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}