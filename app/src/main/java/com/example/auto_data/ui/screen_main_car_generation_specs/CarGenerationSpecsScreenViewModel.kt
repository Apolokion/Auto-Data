package com.example.auto_data.ui.screen_main_car_generation_specs

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CarGenerationSpecsScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()

    val generationSpecsList = mutableStateOf<List<com.example.auto_data.network.CarGenerationSpecs>>(emptyList())
    val generationName = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val isTopAppBarVisible = mutableStateOf(true)

    fun getGenerationSpecs(generationId: String?) {
        if (generationId.isNullOrEmpty()) return

        isLoading.value = true
        error.value = null
        generationName.value = null

        viewModelScope.launch {
            try {
                val generationIdLong = generationId.toLong()

                val allGenerationSpecs = repository.getAllGenerationSpecs()
                val foundSpecs = allGenerationSpecs.filter { it.idGeneration == generationIdLong }

                if (foundSpecs.isEmpty()) {
                    error.value = "Specifications not found for ID: $generationId"
                } else {
                    generationSpecsList.value = foundSpecs
                    generationName.value = foundSpecs.firstOrNull()?.generation
                }

            } catch (e: Exception) {
                error.value = "Loading Error: ${e.message}"
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

    fun refresh(generationId: String?) {
        getGenerationSpecs(generationId)
    }
}