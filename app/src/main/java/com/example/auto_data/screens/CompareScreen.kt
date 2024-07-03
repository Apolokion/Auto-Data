package com.example.auto_data.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auto_data.viewmodel.CompareScreenViewModel

@Composable
fun CompareScreen(viewModel: CompareScreenViewModel = viewModel()) {
    Text("Compare Screen")
}
