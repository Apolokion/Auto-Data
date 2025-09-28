package com.example.auto_data.network

import io.github.jan.supabase.postgrest.postgrest

class SupabaseRepository {

    // 1. Car Brands
    suspend fun getAllCarBrands(): List<CarBrand> {
        return try {
            SupabaseManager.client.postgrest["1. Car Brands"]
                .select()
                .decodeList<CarBrand>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 2. Car Models
    suspend fun getAllCarModels(): List<CarModel> {
        return try {
            SupabaseManager.client.postgrest["2. Car Models"]
                .select()
                .decodeList<CarModel>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 3. Car Generations
    suspend fun getAllCarGenerations(): List<CarGeneration> {
        return try {
            SupabaseManager.client.postgrest["3. Car Generations"]
                .select()
                .decodeList<CarGeneration>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 3.1. Car Generation Specs
    suspend fun getAllGenerationSpecs(): List<CarGenerationSpecs> {
        return try {
            SupabaseManager.client.postgrest["3.1. Car Generation Specs"]
                .select()
                .decodeList<CarGenerationSpecs>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 4. Spec Details - General information
    suspend fun getAllGeneralSpecs(): List<SpecDetailsGeneral> {
        return try {
            SupabaseManager.client.postgrest["4. Spec Details - General information"]
                .select()
                .decodeList<SpecDetailsGeneral>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getGeneralSpecs(genSpecsId: Long): SpecDetailsGeneral? {
        return try {
            val allSpecs = getAllGeneralSpecs()
            allSpecs.find { it.idGenSpecs == genSpecsId }
        } catch (e: Exception) {
            null
        }
    }

    // 5. Spec Details - Performance specs
    suspend fun getAllPerformanceSpecs(): List<SpecDetailsPerformance> {
        return try {
            SupabaseManager.client.postgrest["5. Spec Details - Performance specs"]
                .select()
                .decodeList<SpecDetailsPerformance>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPerformanceSpecs(genSpecsId: Long): SpecDetailsPerformance? {
        return try {
            val allSpecs = getAllPerformanceSpecs()
            allSpecs.find { it.idGenSpecs == genSpecsId }
        } catch (e: Exception) {
            null
        }
    }

    // 6. Spec Details - Engine specs
    suspend fun getAllEngineSpecs(): List<SpecDetailsEngine> {
        return try {
            SupabaseManager.client.postgrest["6. Spec Details - Engine specs"]
                .select()
                .decodeList<SpecDetailsEngine>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getEngineSpecs(genSpecsId: Long): SpecDetailsEngine? {
        return try {
            val allSpecs = getAllEngineSpecs()
            allSpecs.find { it.idGenSpecs == genSpecsId }
        } catch (e: Exception) {
            null
        }
    }

    // 7. Spec Details - Space, Volume and Weights
    suspend fun getAllSpaceVolumeSpecs(): List<SpecDetailsSpaceVolume> {
        return try {
            SupabaseManager.client.postgrest["7. Spec Details - Space, Volume and Weights"]
                .select()
                .decodeList<SpecDetailsSpaceVolume>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getSpaceVolumeSpecs(genSpecsId: Long): SpecDetailsSpaceVolume? {
        return try {
            val allSpecs = getAllSpaceVolumeSpecs()
            allSpecs.find { it.idGenSpecs == genSpecsId }
        } catch (e: Exception) {
            null
        }
    }

    // 8. Spec Details - Dimensions
    suspend fun getAllDimensionsSpecs(): List<SpecDetailsDimensions> {
        return try {
            SupabaseManager.client.postgrest["8. Spec Details - Dimensions"]
                .select()
                .decodeList<SpecDetailsDimensions>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDimensionsSpecs(genSpecsId: Long): SpecDetailsDimensions? {
        return try {
            val allSpecs = getAllDimensionsSpecs()
            allSpecs.find { it.idGenSpecs == genSpecsId }
        } catch (e: Exception) {
            null
        }
    }

    // 9. Spec Details - Drivetrain, brakes and suspension specs
    suspend fun getAllDrivetrainSpecs(): List<SpecDetailsDrivetrain> {
        return try {
            SupabaseManager.client.postgrest["9. Spec Details - Drivetrain, brakes and suspension specs"]
                .select()
                .decodeList<SpecDetailsDrivetrain>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDrivetrainSpecs(genSpecsId: Long): SpecDetailsDrivetrain? {
        return try {
            val allSpecs = getAllDrivetrainSpecs()
            allSpecs.find { it.idGenSpecs == genSpecsId }
        } catch (e: Exception) {
            null
        }
    }
}