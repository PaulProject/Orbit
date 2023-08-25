package com.example.orbit.data.service

import com.example.orbit.data.response.CountryResponse
import retrofit2.http.GET

interface CountryService {

    @GET("country-flag-emoji-json@2.0.0/dist/index.json")
    suspend fun getCountryList(): List<CountryResponse>

}