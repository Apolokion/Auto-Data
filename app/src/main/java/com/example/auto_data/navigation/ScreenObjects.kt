package com.example.auto_data.navigation

sealed class ScreenObjects(val route: String) {
    object Main : ScreenObjects("main_screen")
    object Compare : ScreenObjects("compare_screen")
    object News : ScreenObjects("news_screen")
    object Account : ScreenObjects("account_screen")
    object Settings : ScreenObjects("settings_screen")
    object WishList : ScreenObjects("wish_list_screen")
    object CarModels : ScreenObjects("car_models/{carCompany}") {
        fun createRoute(carCompany: String) = "car_models/$carCompany"
    }

    object CarModelDescription : ScreenObjects("car_model_description/{carModel}") {
        fun createRoute(carModel: String) = "car_model_description/$carModel"
    }

}