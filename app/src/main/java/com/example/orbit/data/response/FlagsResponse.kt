package com.example.orbit.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlagsResponse(
    @Json(name = "png")
    val png: String? = null,
    @Json(name = "alt")
    val alt: String? = null,
)