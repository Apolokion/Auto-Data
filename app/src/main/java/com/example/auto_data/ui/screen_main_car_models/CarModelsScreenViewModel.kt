package com.example.auto_data.ui.screen_main_car_models

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class CarModelsScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()
    val carBrands = mutableStateOf<List<com.example.auto_data.network.CarBrand>>(emptyList())

    val carModels = mutableStateOf<List<com.example.auto_data.network.CarModel>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val isTopAppBarVisible = mutableStateOf(true)

    init {
        loadCarBrands()
    }

    private fun loadCarBrands() {
        viewModelScope.launch {
            try {
                carBrands.value = repository.getAllCarBrands()
            } catch (e: Exception) {
                // Ignoring Error, using fallback
            }
        }
    }

    fun getCarModels(carCompany: String?) {
        if (carCompany.isNullOrEmpty()) return

        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            try {
                val allModels = repository.getAllCarModels()

                // Filter models by brand
                val filteredModels = allModels.filter { model ->
                    model.brandName.equals(carCompany, ignoreCase = true)
                }

                carModels.value = filteredModels.sortedBy { it.modelName }
            } catch (e: Exception) {
                error.value = "Error loading car models: ${e.message}"
                carModels.value = emptyList()
            } finally {
                isLoading.value = false
            }
        }
    }

    fun observeScrollState(listState: LazyListState) {
        viewModelScope.launch {
            snapshotFlow { listState.firstVisibleItemIndex }
                .distinctUntilChanged()
                .collect { firstVisibleItemIndex ->
                    isTopAppBarVisible.value = firstVisibleItemIndex == 0
                }
        }
    }

    fun refresh(carCompany: String?) {
        getCarModels(carCompany)
    }

}
