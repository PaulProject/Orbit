package com.example.orbit.country.data.service

import com.example.orbit.country.data.response.CountryDetailResponse
import com.example.orbit.country.data.response.CountryListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("all?fields=flags,name,cca2")
    suspend fun getCountryList(): List<CountryListResponse>

    @GET("alpha/{code}")
    suspend fun getCountryDetail(@Path("code") code: String): CountryDetailResponse

}