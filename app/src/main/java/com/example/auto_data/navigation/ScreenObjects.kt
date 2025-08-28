package com.example.auto_data.navigation

sealed class ScreenObjects(val route: String) {
    data object Main : ScreenObjects("main_screen")
    data object Compare : ScreenObjects("compare_screen")
    data object News : ScreenObjects("news_screen")
    data object Account : ScreenObjects("account_screen")
    data object Settings : ScreenObjects("settings_screen")
    data object WishList : ScreenObjects("wish_list_screen")
    data object CarModels : ScreenObjects("car_models/{carCompany}") {
        fun createRoute(carCompany: String) = "car_models/$carCompany"
    }
    data object CarGenerations : ScreenObjects("car_generations/{carModelId}/{carModelName}/{carBrand}") {
        fun createRoute(carModelId: String, carModelName: String, carBrand: String) =
            "car_generations/$carModelId/$carModelName/$carBrand"
    }
    data object CarGenerationSpecs : ScreenObjects("generation_specs/{generationId}") {
        fun createRoute(generationId: String) = "generation_specs/$generationId"
    }
}