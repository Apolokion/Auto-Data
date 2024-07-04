package com.example.auto_data.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.auto_data.data.car_Companies

class MainScreenViewModel : ViewModel() {
    val carCompanies = car_Companies
    var isGrid = mutableStateOf(false)
    var isTopAppBarVisible = mutableStateOf(true)

    var lazyListState = LazyListState()
    var lazyGridState = LazyGridState()


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
}
