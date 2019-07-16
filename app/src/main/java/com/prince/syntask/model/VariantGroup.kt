package com.prince.syntask.model

import com.squareup.moshi.Json

data class VariantGroup(
    @Json(name = "group_id")
    val groupId: String,
    val name: String,
    val variations: List<Variation>,
    var selected: String = ""
)