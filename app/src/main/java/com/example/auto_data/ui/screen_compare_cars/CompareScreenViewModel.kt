package com.example.auto_data.ui.screen_compare_cars

import androidx.lifecycle.ViewModel

class CompareScreenViewModel : ViewModel() {
    // Placeholder for actual data
    val carBrands = listOf("Toyota", "Honda", "Ford")
    val carModels = listOf("Model A", "Model B", "Model C")
/*
    private val _brands = mutableStateListOf<Brand>()
    val brands: List<Brand> get() = _brands

    init {
        fetchBrands()
    }

    private fun fetchBrands() {
        Log.d("MainViewModel", "Fetching brands...")
        ApiClient.service.getBrands().enqueue(object : Callback<Brands> {
            override fun onResponse(call: Call<Brands>, response: Response<Brands>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.brands?.let {
                        _brands.addAll(it)
                    }
                    Log.d("MainViewModel", "Brands fetched successfully")
                } else {
                    Log.e("MainViewModel", "Error in response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Brands>, t: Throwable) {
                Log.e("MainViewModel", "Failed to fetch brands", t)
            }
        })
    }

    */
}
