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

    data object CarModelDescription : ScreenObjects("car_model_description/{carModel}") {
        fun createRoute(carModel: String) = "car_model_description/$carModel"
    }

}