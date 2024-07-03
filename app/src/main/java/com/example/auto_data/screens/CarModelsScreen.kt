// CarModelsScreen.kt
package com.example.auto_data.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.viewmodel.CarModelsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarModelsScreen(carCompany: String?, navController: NavHostController, viewModel: CarModelsScreenViewModel = viewModel()) {
    val carModels = viewModel.getCarModels(carCompany)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = carCompany ?: "Car Models")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.height(48.dp)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            items(carModels) { carModel ->
                Text(
                    text = carModel.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate(ScreenObjects.CarModelDescription.createRoute(carModel.name))
                        }
                )
            }
        }
    }
}
