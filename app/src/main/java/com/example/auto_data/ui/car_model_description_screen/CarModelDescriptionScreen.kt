package com.example.auto_data.ui.car_model_description_screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.auto_data.R
import com.example.auto_data.ui.theme.Dimensions.icon_size_normal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarModelDescriptionScreen(
    carModel: String?,
    navController: NavHostController,
    viewModel: CarModelDescriptionViewModel = viewModel()
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
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$carModel model description",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painterResource(
                                id = R.drawable.arrow_back
                            ),
                            contentDescription = "Back",
                            modifier = Modifier.size(icon_size_normal),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(
            text = "Description for $carModel",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}
