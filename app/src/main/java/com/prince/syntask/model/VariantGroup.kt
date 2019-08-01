package com.prince.syntask.model

data class VariantGroup(
    val groupId: String,
    val name: String,
    val variations: List<Variation>,
    var selected: String = ""
)