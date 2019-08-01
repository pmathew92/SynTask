package com.prince.data.model

import com.squareup.moshi.Json

data class ResponseWrapper(
    @Json(name = "variants")
    val variantsNetwork: VariantsNetwork
)