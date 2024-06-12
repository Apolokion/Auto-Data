package com.example.auto_data.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.ui.theme.icon_size_small

// Define CarCompany data class
data class CarCompany(val name: String, val icon: Int)

// List of car companies
val carCompanies = listOf(
    CarCompany("Aston Martin", R.drawable.aston),
    CarCompany("Audi", R.drawable.audi),
    CarCompany("Alfa Romeo", R.drawable.alfa),
    CarCompany("Bentley", R.drawable.bentley),
    CarCompany("BMW", R.drawable.bmw),
    CarCompany("Ferrari", R.drawable.ferrari),
    CarCompany("Ford ", R.drawable.ford),
    CarCompany("Jaguar ", R.drawable.jaguar),
    CarCompany("Land Rover", R.drawable.land),
    CarCompany("Lexus", R.drawable.lexus),
    CarCompany("Maserati ", R.drawable.maserati),
    CarCompany("Aston Martin", R.drawable.aston),
    CarCompany("BMW", R.drawable.bmw),
    CarCompany("Aston Martin", R.drawable.aston),
    // Add more car companies here
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var isGrid by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(48.dp),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(start = 28.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Car Brands",
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isGrid = !isGrid }) {
                        Icon(
                            painter = if (isGrid) painterResource(id = R.drawable.list) else painterResource(
                                id = R.drawable.menu
                            ),
                            contentDescription = "Toggle View",
                            modifier = Modifier.size(icon_size_small),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        if (isGrid) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(carCompanies) { carCompany ->
                    CarCompanyItem(carCompany, navController, isGrid = true)
                }
            }
        } else {
            LazyColumn(contentPadding = innerPadding) {
                items(carCompanies) { carCompany ->
                    CarCompanyItem(carCompany, navController, isGrid = false)
                }
            }
        }
    }
}

@Composable
fun CarCompanyItem(carCompany: CarCompany, navController: NavHostController, isGrid: Boolean) {
    val borderModifier = Modifier.border(width = 1.dp, color = Color.Black)
    val paddingModifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
    val clickableModifier = Modifier.clickable {
        navController.navigate("car_models/${carCompany.name}")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(borderModifier)
            .then(paddingModifier)
            .then(clickableModifier),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = carCompany.icon),
                contentDescription = "Car Icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = carCompany.name)
        }
    }
}

