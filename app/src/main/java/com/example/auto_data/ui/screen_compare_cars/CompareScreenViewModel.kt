package com.example.auto_data.ui.screen_compare_cars

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.auto_data.network.CarBrand
import com.example.auto_data.network.CarGeneration
import com.example.auto_data.network.CarGenerationSpecs
import com.example.auto_data.network.CarModel
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.launch

class CompareScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()
    val allBrands = mutableStateOf<List<CarBrand>>(emptyList())

    val leftModels = mutableStateOf<List<CarModel>>(emptyList())
    val leftGenerations = mutableStateOf<List<CarGeneration>>(emptyList())
    val leftSpecs = mutableStateOf<List<CarGenerationSpecs>>(emptyList())
    val rightModels = mutableStateOf<List<CarModel>>(emptyList())
    val rightGenerations = mutableStateOf<List<CarGeneration>>(emptyList())
    val rightSpecs = mutableStateOf<List<CarGenerationSpecs>>(emptyList())

    // Select Values
    val selectedLeftBrand = mutableStateOf<CarBrand?>(null)
    val selectedLeftModel = mutableStateOf<CarModel?>(null)
    val selectedLeftGeneration = mutableStateOf<CarGeneration?>(null)
    val selectedLeftSpecs = mutableStateOf<CarGenerationSpecs?>(null)

    val selectedRightBrand = mutableStateOf<CarBrand?>(null)
    val selectedRightModel = mutableStateOf<CarModel?>(null)
    val selectedRightGeneration = mutableStateOf<CarGeneration?>(null)
    val selectedRightSpecs = mutableStateOf<CarGenerationSpecs?>(null)

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    init {
        loadBrands()
    }

    private fun loadBrands() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                allBrands.value = repository.getAllCarBrands().sortedBy { it.brandName }
            } catch (e: Exception) {
                error.value = "Error loading brands: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun loadLeftModels(brandId: Long) {
        viewModelScope.launch {
            val allModels = repository.getAllCarModels()
            leftModels.value = allModels
                .filter { it.idBrand == brandId }
                .sortedBy { it.modelName }
        }
    }

    fun loadLeftGenerations(modelId: Long) {
        viewModelScope.launch {
            val allGenerations = repository.getAllCarGenerations()
            leftGenerations.value = allGenerations
                .filter { it.idModel == modelId }
                .sortedBy { it.years }
        }
    }

    fun loadLeftSpecs(generationId: Long) {
        viewModelScope.launch {
            val allSpecs = repository.getAllGenerationSpecs()
            leftSpecs.value = allSpecs.filter { it.idGeneration == generationId }
        }
    }

    fun loadRightModels(brandId: Long) {
        viewModelScope.launch {
            val allModels = repository.getAllCarModels()
            rightModels.value = allModels
                .filter { it.idBrand == brandId }
                .sortedBy { it.modelName }
        }
    }

    fun loadRightGenerations(modelId: Long) {
        viewModelScope.launch {
            val allGenerations = repository.getAllCarGenerations()
            rightGenerations.value = allGenerations
                .filter { it.idModel == modelId }
                .sortedBy { it.years }
        }
    }

    fun loadRightSpecs(generationId: Long) {
        viewModelScope.launch {
            val allSpecs = repository.getAllGenerationSpecs()
            rightSpecs.value = allSpecs.filter { it.idGeneration == generationId }
        }
    }

    // Left Side processing
    fun onLeftBrandSelected(brand: CarBrand?) {
        selectedLeftBrand.value = brand
        selectedLeftModel.value = null
        selectedLeftGeneration.value = null
        selectedLeftSpecs.value = null
        leftModels.value = emptyList()
        leftGenerations.value = emptyList()
        leftSpecs.value = emptyList()

        brand?.let { loadLeftModels(it.id) }
    }

    fun onLeftModelSelected(model: CarModel?) {
        selectedLeftModel.value = model
        selectedLeftGeneration.value = null
        selectedLeftSpecs.value = null
        leftGenerations.value = emptyList()
        leftSpecs.value = emptyList()

        model?.let { loadLeftGenerations(it.id) }
    }

    fun onLeftGenerationSelected(generation: CarGeneration?) {
        selectedLeftGeneration.value = generation
        selectedLeftSpecs.value = null
        leftSpecs.value = emptyList()

        generation?.let { loadLeftSpecs(it.id) }
    }

    fun onLeftSpecsSelected(specs: CarGenerationSpecs?) {
        selectedLeftSpecs.value = specs
    }

    // Right Side processing
    fun onRightBrandSelected(brand: CarBrand?) {
        selectedRightBrand.value = brand
        selectedRightModel.value = null
        selectedRightGeneration.value = null
        selectedRightSpecs.value = null
        rightModels.value = emptyList()
        rightGenerations.value = emptyList()
        rightSpecs.value = emptyList()

        brand?.let { loadRightModels(it.id) }
    }

    fun onRightModelSelected(model: CarModel?) {
        selectedRightModel.value = model
        selectedRightGeneration.value = null
        selectedRightSpecs.value = null
        rightGenerations.value = emptyList()
        rightSpecs.value = emptyList()

        model?.let { loadRightGenerations(it.id) }
    }

    fun onRightGenerationSelected(generation: CarGeneration?) {
        selectedRightGeneration.value = generation
        selectedRightSpecs.value = null
        rightSpecs.value = emptyList()

        generation?.let { loadRightSpecs(it.id) }
    }

    fun onRightSpecsSelected(specs: CarGenerationSpecs?) {
        selectedRightSpecs.value = specs
    }
}
