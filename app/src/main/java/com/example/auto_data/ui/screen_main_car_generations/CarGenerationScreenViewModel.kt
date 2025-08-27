package com.example.auto_data.ui.screen_main_car_generations

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CarGenerationsScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()

    val carGenerations = mutableStateOf<List<com.example.auto_data.network.CarGeneration>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val isTopAppBarVisible = mutableStateOf(true)

    fun getCarGenerations(carModelId: String?, carBrand: String?) {
        if (carModelId.isNullOrEmpty()) return

        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            try {
                val allGenerations = repository.getAllCarGenerations()

                // Converting carModelId in Long for compare
                val modelIdLong = try {
                    carModelId.toLong()
                } catch (e: Exception) {
                    -1L
                }

                // Filter by model Id
                val filteredGenerations = allGenerations.filter { generation ->
                    generation.idModel == modelIdLong
                }

                carGenerations.value = filteredGenerations.sortedBy { it.years }

                // If no info by id, then by name (fallback)
                if (filteredGenerations.isEmpty()) {
                    val fallbackGenerations = allGenerations.filter { generation ->
                        generation.brandName.equals(carBrand, ignoreCase = true) &&
                                generation.generation?.contains(carModelId, ignoreCase = true) == true
                    }
                    if (fallbackGenerations.isNotEmpty()) {
                        carGenerations.value = fallbackGenerations.sortedBy { it.years }
                    }
                }

            } catch (e: Exception) {
                error.value = "Error loading generations: ${e.message}"
                carGenerations.value = emptyList()
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

    fun refresh(carModel: String?, carBrand: String?) {
        getCarGenerations(carModel, carBrand)
    }
}