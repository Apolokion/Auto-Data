package com.example.auto_data.ui.screen_main_car_generation_specs

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.navigation.ScreenObjects
import com.example.auto_data.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarGenerationSpecsScreen(
    generationId: String?,
    navController: NavHostController,
    viewModel: CarGenerationSpecsScreenViewModel = viewModel()
) {
    val generationSpecsList by viewModel.generationSpecsList
    val generationName by viewModel.generationName
    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val isTopAppBarVisible by viewModel.isTopAppBarVisible

    val topAppBarOffset by animateDpAsState(
        targetValue = if (isTopAppBarVisible) 0.dp else -Dimensions.topAppBarHeight,
        label = "topAppBarOffset"
    )

    val listState = rememberLazyListState()

    // Loading data when screen is opened
    LaunchedEffect(generationId) {
        if (generationId != null) {
            viewModel.getGenerationSpecs(generationId)
        }
    }

    LaunchedEffect(listState) {
        viewModel.observeScrollState(listState)
    }

    Scaffold(
        topBar = {
            CarGenerationSpecsTopAppBar(
                topAppBarOffset = topAppBarOffset.value,
                navController = navController,
                generationName = generationName,
                brand = generationSpecsList.firstOrNull()?.brand,
            )
        }
    ) { innerPadding ->
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
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Click here to retry",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .clickable {
                            viewModel.refresh(generationId)
                        }
                    )
                }
            }
        }
        else if (generationSpecsList.isNotEmpty()) {
            LazyColumn(
                state = listState,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize()
            ) {
                items(generationSpecsList) { specs ->
                    Column {
                        GenerationSpecsItem(specs = specs, navController = navController)
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                        )
                    }
                }
            }
        }
        else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Specification not found",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun GenerationSpecsItem(
    specs: com.example.auto_data.network.CarGenerationSpecs,
    navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    ScreenObjects.CarSpecsDetails.createRoute(
                        specsId = specs.id.toString()
                    )
                )
            }
            .padding(horizontal = 4.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            specs.genSpecs?.let { genSpecs ->
                if (genSpecs.isNotBlank()) {
                    Text(
                        text = genSpecs,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            specs.brand?.let { brand ->
                Text(
                    text = "Brand: $brand",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            specs.generation?.let { generation ->
                Text(
                    text = "Generation: $generation",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 4.dp),
                    )
            }

            specs.years?.let { years ->
                if (years.isNotBlank()) {
                    Text(
                        text = "Years: $years",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
            }

            Text(
                text = "ID Specs: ${specs.id}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 4.dp),
            )
            Text(
                text = "ID Model: ${specs.idModel}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = "ID Generation: ${specs.idGeneration}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarGenerationSpecsTopAppBar(
    topAppBarOffset: Float,
    navController: NavHostController,
    generationName: String?,
    brand: String?,
) {
    TopAppBar(
        modifier = Modifier
            .height(Dimensions.topAppBarHeight)
            .offset(y = topAppBarOffset.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (generationName != null) {
                        "Specifications for $brand $generationName"
                    } else {
                        "No Specifications Data"
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1
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
