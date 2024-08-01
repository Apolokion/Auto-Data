package com.example.auto_data.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.data.CarModel
import com.example.auto_data.data.carModelsMap
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


class CarModelsScreenViewModel : ViewModel() {

    val carModels = mutableStateOf<List<CarModel>>(emptyList())
    val isTopAppBarVisible = mutableStateOf(true)

    fun getCarModels(carCompany: String?) {
        carCompany?.let {
            carModels.value = carModelsMap[it] ?: emptyList()
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

}
