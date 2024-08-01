package com.example.auto_data.ui.carModels

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.data.CarModel
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.ui.theme.Dimensions
<<<<<<< HEAD:app/src/main/java/com/example/auto_data/screens/CarModelsScreen.kt
import com.example.auto_data.viewmodel.CarModelsScreenViewModel
=======
import com.example.auto_data.ui.theme.Dimensions.icon_size_normal
>>>>>>> a093cc581761bd7089e31d9449fd9dba4a6a1a01:app/src/main/java/com/example/auto_data/ui/carModels/CarModelsScreen.kt

@Composable
fun CarModelsScreen(
    carCompany: String?,
    navController: NavHostController,
    viewModel: CarModelsScreenViewModel = viewModel()
) {
    // Observe car models from ViewModel
    val carModels by viewModel.carModels

    // Observe top app bar visibility state from ViewModel
    val isTopAppBarVisible by viewModel.isTopAppBarVisible

    // Animate the top app bar offset based on visibility
    val topAppBarOffset by animateDpAsState(
        targetValue = if (isTopAppBarVisible) 0.dp else -Dimensions.topAppBarHeight,
        label = "topAppBarOffset"
    )

    // Remember the state of the LazyColumn
    val listState = rememberLazyListState()

    // Load car models when the screen is composed
    viewModel.getCarModels(carCompany)

    // Observe scroll state changes to hide/show the top app bar
    LaunchedEffect(listState) {
        viewModel.observeScrollState(listState)
        }

    Scaffold(
        topBar = {
            CarModelsTopAppBar(topAppBarOffset.value, navController, carCompany)
        }
    ) { innerPadding ->
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
}


@Composable
fun CarModelItem(carModel: CarModel, navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    ScreenObjects.CarModelDescription.createRoute(carModel.name)
                )
            }
            .padding(
                horizontal = Dimensions.padding_small
            )
    ) {
        carModel.icon?.let {
            Image(
                painter = painterResource(id = carModel.icon),
                contentDescription = null,
                modifier = Modifier.size(Dimensions.model_image)
            )
        }
        Spacer(modifier = Modifier.width(Dimensions.spacer_large))
        Text(
            text = carModel.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
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
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(Dimensions.icon_size_normal),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    )
}

