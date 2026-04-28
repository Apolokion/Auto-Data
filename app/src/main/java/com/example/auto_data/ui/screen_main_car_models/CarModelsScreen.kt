package com.example.auto_data.ui.screen_main_car_models

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.auto_data.R
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.ui.theme.Dimensions

@Composable
fun CarModelsScreen(
    carCompany: String?,
    navController: NavHostController,
    viewModel: CarModelsScreenViewModel = viewModel()
) {
    val carModels by viewModel.carModels
    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val isTopAppBarVisible by viewModel.isTopAppBarVisible

    val topAppBarOffset by animateDpAsState(
        targetValue = if (isTopAppBarVisible) 0.dp else -Dimensions.topAppBarHeight,
        label = "topAppBarOffset"
    )

    val listState = rememberLazyListState()

    // Load car models when the screen is composed or company changes
    LaunchedEffect(carCompany) {
        if (carCompany != null) {
            viewModel.getCarModels(carCompany)
        }
    }

    // Observe scroll state changes to hide/show the top app bar
    LaunchedEffect(listState) {
        viewModel.observeScrollState(listState)
    }

    Scaffold(
        topBar = {
            CarModelsTopAppBar(topAppBarOffset.value, navController, carCompany)
        }
    ) { innerPadding ->

        // Loading Indicator
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        // Showing Error
        else if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = error!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Click to retry",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable {
                            viewModel.refresh(carCompany)
                        }
                    )
                }
            }
        }
        // Showing Data
        else if (carModels.isNotEmpty()) {
            LazyColumn(
                state = listState,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(carModels) { carModel ->
                    Column {
                        CarModelItem(carModel, navController)
                        HorizontalDivider(thickness = 1.dp, color = Color.Black)
                    }
                }
            }
        }
        // Empty List if no Data
        else if (!isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No models found for $carCompany",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun CarModelItem(
    carModel: com.example.auto_data.network.CarModel,
    navController: NavHostController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                navController.navigate(
                    ScreenObjects.CarGenerations.createRoute(
                        carModelId = carModel.id.toString(),
                        carModelName = carModel.modelName ?: "",
                        carBrand = carModel.brandName ?: ""
                    )
                )
            }
    ) {
        if (!carModel.urlPictures.isNullOrEmpty()) {
            Card {
                Image(
                    painter = rememberAsyncImagePainter(carModel.urlPictures),
                    contentDescription = "Car Model Image",
                    modifier = Modifier
                        .size(width = 210.dp, height = 130.dp),
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Default Car Icon",
                modifier = Modifier.size(Dimensions.model_image)
            )
        }

        Spacer(modifier = Modifier.width(Dimensions.spacer_large))

        Column {
            Text(
                text = carModel.modelName ?: "Unknown Model",
                style = MaterialTheme.typography.titleMedium
            )
            carModel.years?.let { years ->
                if (years.isNotBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = years,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarModelsTopAppBar(
    topAppBarOffset: Float,
    navController: NavHostController,
    carCompany: String?
) {
    TopAppBar(
        modifier = Modifier
            .height(Dimensions.topAppBarHeight)
            .offset(y = topAppBarOffset.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.surface
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$carCompany models",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(Dimensions.icon_size_normal),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    )
}