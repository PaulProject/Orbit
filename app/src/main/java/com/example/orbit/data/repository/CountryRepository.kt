package com.example.orbit.data.repository

import com.example.orbit.data.response.CountryResponse
import com.example.orbit.data.service.CountryService
import javax.inject.Inject

interface ICountryRepository {
    suspend fun getCountryList(): List<CountryResponse>
}

internal class CountryRepository @Inject constructor(
    private val service: CountryService,
): ICountryRepository {
    override suspend fun getCountryList(): List<CountryResponse> =
        service.getCountryList()
}