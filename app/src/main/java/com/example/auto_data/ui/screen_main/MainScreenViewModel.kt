package com.example.auto_data.ui.screen_main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()


    val carBrands = mutableStateOf<List<com.example.auto_data.network.CarBrand >>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    var isGrid = mutableStateOf(false)
    var isTopAppBarVisible = mutableStateOf(true)

    var lazyListState = LazyListState()
    var lazyGridState = LazyGridState()

    init {
        loadCarBrands()
    }

    fun loadCarBrands() {
        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            try {
                val brands = repository.getAllCarBrands()
                carBrands.value = brands
            } catch (e: Exception) {
                error.value = "Loading error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }


    fun updateTopAppBarVisibility() {
        if (isGrid.value) {
            isTopAppBarVisible.value = lazyGridState.firstVisibleItemIndex == 0
        } else {
            isTopAppBarVisible.value = lazyListState.firstVisibleItemIndex == 0
        }
    }

    fun toggleView() {
        isGrid.value = !isGrid.value
    }

    fun refresh() {
        loadCarBrands()
    }
}
