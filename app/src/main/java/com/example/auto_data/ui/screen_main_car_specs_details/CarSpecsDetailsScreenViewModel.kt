package com.example.auto_data.ui.screen_main_car_specs_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auto_data.network.SupabaseRepository
import kotlinx.coroutines.launch

class CarSpecsDetailsScreenViewModel : ViewModel() {
    private val repository = SupabaseRepository()

    val generationSpecs = mutableStateOf<com.example.auto_data.network.CarGenerationSpecs?>(null)
    val generalSpecs = mutableStateOf<com.example.auto_data.network.SpecDetailsGeneral?>(null)
    val performanceSpecs = mutableStateOf<com.example.auto_data.network.SpecDetailsPerformance?>(null)
    val engineSpecs = mutableStateOf<com.example.auto_data.network.SpecDetailsEngine?>(null)
    val spaceVolumeSpecs = mutableStateOf<com.example.auto_data.network.SpecDetailsSpaceVolume?>(null)
    val dimensionsSpecs = mutableStateOf<com.example.auto_data.network.SpecDetailsDimensions?>(null)
    val drivetrainSpecs = mutableStateOf<com.example.auto_data.network.SpecDetailsDrivetrain?>(null)

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val isTopAppBarVisible = mutableStateOf(true)
    fun getSpecsDetails(specsId: String?) {
        if (specsId.isNullOrEmpty()) return

        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            try {
                val specsIdLong = specsId.toLong()

                // Retrieving basic information about the specification
                val allGenerationSpecs = repository.getAllGenerationSpecs()
                val foundGenerationSpecs = allGenerationSpecs.find { it.id == specsIdLong }

                if (foundGenerationSpecs == null) {
                    error.value = "Specifications not found for ID: $specsId"
                    return@launch
                }

                generationSpecs.value = foundGenerationSpecs

                // Retrieving detailed information about the specification
                generalSpecs.value = repository.getGeneralSpecs(specsIdLong)
                performanceSpecs.value = repository.getPerformanceSpecs(specsIdLong)
                engineSpecs.value = repository.getEngineSpecs(specsIdLong)
                spaceVolumeSpecs.value = repository.getSpaceVolumeSpecs(specsIdLong)
                dimensionsSpecs.value = repository.getDimensionsSpecs(specsIdLong)
                drivetrainSpecs.value = repository.getDrivetrainSpecs(specsIdLong)

            } catch (e: Exception) {
                error.value = "Loading error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}