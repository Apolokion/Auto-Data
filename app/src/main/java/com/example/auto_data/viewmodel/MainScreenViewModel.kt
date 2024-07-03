package com.example.auto_data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.auto_data.data.car_Companies

class MainViewModel : ViewModel() {
    val carCompanies = car_Companies
    var isGrid = mutableStateOf(false)

    fun toggleView() {
        isGrid.value = !isGrid.value
    }
}
