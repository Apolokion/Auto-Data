package com.example.auto_data.ui.main

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.data.CarCompany
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
                            "Car Brands",
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
                items(viewModel.carCompanies) { carCompany ->
                    CarCompanyItem(carCompany, navController, isGrid = true)
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
                items(viewModel.carCompanies) { carCompany ->
                    CarCompanyItem(carCompany, navController, isGrid = false)
                    HorizontalDivider(thickness = 1.dp, color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun CarCompanyItem(carCompany: CarCompany, navController: NavHostController, isGrid: Boolean) {

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
                    navController.navigate(ScreenObjects.CarModels.createRoute(carCompany.name))
                }
        ) {
            Image(
                painter = painterResource(id = carCompany.icon),
                contentDescription = "Car Icon",
                modifier = Modifier.size(Dimensions.car_icons)
            )
            Spacer(modifier = Modifier.height(Dimensions.spacer_normal))
            Text(text = carCompany.name, style = MaterialTheme.typography.titleSmall)
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(ScreenObjects.CarModels.createRoute(carCompany.name))
                }
                .padding(
                    vertical = Dimensions.padding_normal,
                    horizontal = Dimensions.padding_normal
                )
        ) {
            Image(
                painter = painterResource(id = carCompany.icon),
                contentDescription = "Car Icon",
                modifier = Modifier.size(Dimensions.car_icons)
            )
            Spacer(modifier = Modifier.width(Dimensions.spacer_large))
            Text(text = carCompany.name, style = MaterialTheme.typography.titleSmall)
        }
    }
}
