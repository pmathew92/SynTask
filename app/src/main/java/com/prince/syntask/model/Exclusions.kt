package com.prince.syntask.model

import com.squareup.moshi.Json

data class Exclusions(
    @Json(name = "group_id")
    val groupId: String,
    @Json(name = "variation_id")
    val variationId: String
)