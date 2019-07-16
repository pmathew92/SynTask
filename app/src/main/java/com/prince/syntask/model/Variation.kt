package com.prince.syntask.model

data class Variation(
    val default: Int,
    val id: String,
    val inStock: Int,
    val name: String,
    val price: Int
)