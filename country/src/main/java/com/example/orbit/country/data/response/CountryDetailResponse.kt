package com.example.orbit.country.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDetailResponse(
    @Json(name = "name")
    val name: NameResponse? = null,
    @Json(name = "tld")
    val tld: List<String>? = null,
    @Json(name = "flags")
    val flags: FlagsResponse? = null,
    @Json(name = "currencies")
    val currencies: Map<String, CurrenciesResponse>? = null,
    @Json(name = "idd")
    val idd: IddResponse? = null,
    @Json(name = "capital")
    val capital: List<String>? = null,
)

@JsonClass(generateAdapter = true)
data class CurrenciesResponse(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "symbol")
    val symbol: String? = null,
)

@JsonClass(generateAdapter = true)
data class IddResponse(
    @Json(name = "root")
    val root: String? = null,
    @Json(name = "suffixes")
    val suffixes: List<String>? = null,
)
