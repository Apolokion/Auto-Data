package com.example.auto_data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.auto_data.data.CarModel
import com.example.auto_data.data.carModelsMap

class CarModelsScreenViewModel : ViewModel() {
    fun getCarModels(carCompany: String?): List<CarModel> {
        return carModelsMap[carCompany] ?: emptyList()
    }
}
