// MainViewModel.kt
package com.example.auto_data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    var isGrid = mutableStateOf(false)

    fun toggleView() {
        isGrid.value = !isGrid.value
    }
}
