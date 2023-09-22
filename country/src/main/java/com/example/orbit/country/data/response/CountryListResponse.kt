package com.example.orbit.country.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryListResponse(
    @Json(name = "name")
    val name: NameResponse? = null,
    @Json(name = "flags")
    val flags: FlagsResponse? = null,
    @Json(name = "cca2")
    val cca2: String? = null,
)
