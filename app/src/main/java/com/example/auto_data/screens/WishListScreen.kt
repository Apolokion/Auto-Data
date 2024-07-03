package com.example.auto_data.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auto_data.viewmodel.WishListScreenViewModel

@Composable
fun WishListScreen(viewModel: WishListScreenViewModel = viewModel()) {
    Text("Wish List Screen")
}
