package com.example.orbit.data.repository

import com.example.orbit.data.response.CountryDetailResponse
import com.example.orbit.data.response.CountryListResponse
import com.example.orbit.data.service.CountryService
import javax.inject.Inject

interface ICountryRepository {
    suspend fun getCountryList(): List<CountryListResponse>
    suspend fun getCountryDetailByCode(code: String): CountryDetailResponse
}

internal class CountryRepository @Inject constructor(
    private val service: CountryService,
): ICountryRepository {

    override suspend fun getCountryList(): List<CountryListResponse> =
        service.getCountryList()

    override suspend fun getCountryDetailByCode(code: String): CountryDetailResponse =
        service.getCountryDetail(code)

}