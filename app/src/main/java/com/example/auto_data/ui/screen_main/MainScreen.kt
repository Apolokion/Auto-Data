package com.example.auto_data.ui.screen_main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.auto_data.R
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.ui.theme.Dimensions
import com.example.auto_data.ui.theme.Dimensions.icon_size_small

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = viewModel()
) {

    val isTopAppBarVisible by viewModel.isTopAppBarVisible
    val topAppBarOffset by animateDpAsState(
        targetValue = if (isTopAppBarVisible) 0.dp else (-48).dp,
        label = "topAppBarOffset"
    )

    val carBrands by viewModel.carBrands
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(48.dp)
                    .offset(y = topAppBarOffset),
                colors = TopAppBarDefaults.topAppBarColors(
                    MaterialTheme.colorScheme.surface
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(start = 28.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Manufacturers",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleView() }) {
                        Icon(
                            painter = if (viewModel.isGrid.value) painterResource(
                                id = R.drawable.list
                            ) else painterResource(
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
        // In case Error
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
                        text = "Click here to reload",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable { viewModel.refresh() }
                    )
                }
            }
        }
        // Showing Data
        else if (carBrands.isNotEmpty()) {
            if (viewModel.isGrid.value) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = innerPadding,
                    modifier = Modifier
                        .fillMaxSize()
                        .onGloballyPositioned {
                            viewModel.updateTopAppBarVisibility()
                        },
                    state = viewModel.lazyGridState
                ) {
                    items(carBrands) { carBrand ->
                        CarBrandItem(carBrand, navController, isGrid = true)
                        HorizontalDivider(thickness = 1.dp, color = Color.Black)
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = innerPadding,
                    modifier = Modifier
                        .onGloballyPositioned {
                            viewModel.updateTopAppBarVisibility()
                        },
                    state = viewModel.lazyListState
                ) {
                    items(carBrands) { carBrand ->
                        CarBrandItem(carBrand, navController, isGrid = false)
                        HorizontalDivider(thickness = 1.dp, color = Color.Black)
                    }
                }
            }
        }
        // Else - Empty List
        else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No data",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun CarBrandItem(carBrand: com.example.auto_data.network.CarBrand, navController: NavHostController, isGrid: Boolean) {

    if (isGrid) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            modifier = Modifier
                .padding(
                    vertical = Dimensions.padding_normal,
                    horizontal = Dimensions.padding_small
                )
                .fillMaxWidth()
                .clickable {
                    navController.navigate(ScreenObjects.CarModels.createRoute(carBrand.brandName ?: ""))
                }
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                if (!carBrand.icon.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(carBrand.icon),
                        contentDescription = "Car Brand Icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = "Default Car Icon",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Spacer(modifier = Modifier.height(Dimensions.spacer_normal))
            Text(
                text = carBrand.brandName ?: "Unknown Brand",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(ScreenObjects.CarModels.createRoute(carBrand.brandName ?: ""))
                }
                .padding(
                    vertical = Dimensions.padding_normal,
                    horizontal = Dimensions.padding_normal
                )
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp),
                contentAlignment = Alignment.Center
            ) {
                if (!carBrand.icon.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(carBrand.icon),
                        contentDescription = "Car Brand Icon",
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = "Default Car Icon",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Spacer(modifier = Modifier.width(Dimensions.spacer_large))
            Text(
                text = carBrand.brandName ?: "Unknown Brand",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}