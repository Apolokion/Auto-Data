package com.example.auto_data.api

import kotlinx.serialization.Serializable

@Serializable
data class Brands(
    val brands: List<Brand>
)

@Serializable
data class Brand(
    val name: String,
    val models: List<Model>
)

@Serializable
data class Model(
    val name: String
)
