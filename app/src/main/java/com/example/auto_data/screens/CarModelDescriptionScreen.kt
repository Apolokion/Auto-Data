// CarModelDescriptionScreen.kt
package com.example.auto_data.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.auto_data.ui.theme.Dimensions
import com.example.auto_data.viewmodel.CarModelDescriptionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarModelDescriptionScreen(carModel: String?, navController: NavHostController, viewModel: CarModelDescriptionViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = carModel ?: "Car Model Description")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                modifier = Modifier.height(Dimensions.padding_xlarge)
            )
        }
    ) { innerPadding ->
        Text(
            text = "Description for $carModel",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        )
    }
}
