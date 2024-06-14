package com.example.auto_data.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
    object Compare : Screen("compare_screen")
    object News : Screen("news_screen")
    object Account : Screen("account_screen")
    object Settings : Screen("settings_screen")
    object WishList : Screen("wish_list_screen")
    object CarModels : Screen("car_models/{carCompany}") {
        fun createRoute(carCompany: String) = "car_models/$carCompany"
    }
    object CarModelDescription : Screen("car_model_description/{carModel}") {
        fun createRoute(carModel: String) = "car_model_description/$carModel"
    }

}