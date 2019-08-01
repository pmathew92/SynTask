package com.prince.domain.entities

data class VariationEntity(
    val default: Int,
    val id: String,
    val inStock: Int,
    val name: String,
    val price: Int
)