package com.example.auto_data.ui.screen_wishlist

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val engineSize: String,
    val fuelType: String,
    val drivetrain: String,
    val imageUrl: String
)

class WishListScreenViewModel : ViewModel() {
    private val _wishlist = mutableStateListOf<Car>()
    val wishlist: List<Car> = _wishlist

    init {
        // Add some sample data
        _wishlist.addAll(
            listOf(
                Car("Toyota", "Corolla", 2020, "1.8L", "Petrol", "FWD", "https://di-uploads-pod14.dealerinspire.com/toyotaoforlando/uploads/2019/02/2020-Toyota-Corolla.jpg"),
                Car("Honda", "Civic", 2021, "2.0L", "Petrol", "FWD", "https://www.corwinhondakalispell.com/static/dealer-18353/may_featured_image.JPG"),
                Car("Ford", "Mustang", 2019, "5.0L", "Petrol", "RWD", "https://www.topgear.com/sites/default/files/images/cars-road-test/carousel/2017/11/a0c9ebba0bde5cbe2ddd30b3eafdef52/magnetic_mustang_3.jpg?w=1784&h=1004")
            )
        )
    }

    fun removeCar(car: Car) {
        _wishlist.remove(car)
    }
}
