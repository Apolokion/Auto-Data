package com.example.auto_data.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.data.CarCompany
import com.example.auto_data.data.carCompanies
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.ui.theme.icon_size_small

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
    val paddingModifier = if (isGrid) {
        Modifier.padding(8.dp)
    } else {
        Modifier.padding(vertical = 12.dp, horizontal = 12.dp)
    }
    val clickableModifier = Modifier.clickable {
        navController.navigate(ScreenObjects.CarModels.createRoute(carCompany.name))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(borderModifier)
            .then(paddingModifier)
            .then(clickableModifier),
    ) {
        if (isGrid) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = carCompany.icon),
                    contentDescription = "Car Icon",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier
                    .height(8.dp)

                )
                Text(text = carCompany.name, style = MaterialTheme.typography.titleSmall)
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = carCompany.icon),
                    contentDescription = "Car Icon",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = carCompany.name, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}

