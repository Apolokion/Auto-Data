package com.example.auto_data.data

data class CarModel(val name: String)

val carModelsMap = mapOf(
    "Audi" to listOf(CarModel("A3"), CarModel("A4"), CarModel("A6")),
    "BMW" to listOf(CarModel("X1"), CarModel("X3"), CarModel("X5")),
    // Add more car models for other brands here
)
