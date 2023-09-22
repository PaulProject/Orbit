package com.example.orbit.country.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NameResponse(
    @Json(name = "common")
    val common: String? = null,
    @Json(name = "official")
    val official: String? = null,
)