package com.example.auto_data.ui.screen_car_models

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.data.CarModel
import com.example.auto_data.data.carModelsMap
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class CarModelsScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()

    val carModels = mutableStateOf<List<com.example.auto_data.network.CarModel>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val isTopAppBarVisible = mutableStateOf(true)

    fun getCarModels(carCompany: String?) {
        if (carCompany.isNullOrEmpty()) return

        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            try {
                val allModels = repository.getAllCarModels()

                val filteredModels = allModels.filter { model ->
                    model.brandName.equals(carCompany, ignoreCase = true)
                }

                carModels.value = filteredModels
            } catch (e: Exception) {
                error.value = "Error loading models: ${e.message}"
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
