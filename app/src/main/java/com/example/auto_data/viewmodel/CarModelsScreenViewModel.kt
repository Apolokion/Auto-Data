package com.example.auto_data.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.auto_data.data.CarModel
import com.example.auto_data.data.carModelsMap

class CarModelsScreenViewModel : ViewModel() {

    val carModels = mutableStateOf<List<CarModel>>(emptyList())
    val isTopAppBarVisible = mutableStateOf(true)

    fun getCarModels(carCompany: String?) {
        carCompany?.let {
            carModels.value = carModelsMap[it] ?: emptyList()
        }
    }

}
