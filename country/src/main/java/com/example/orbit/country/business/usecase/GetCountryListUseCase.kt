package com.example.orbit.country.business.usecase

import com.example.orbit.country.data.repository.ICountryRepository
import com.example.orbit.country.data.response.CountryListResponse
import javax.inject.Inject

interface IGetCountryListUseCase {
    suspend operator fun invoke(): List<CountryListResponse>
}

class GetCountryListUseCase @Inject constructor(
    private val repository: ICountryRepository,
): IGetCountryListUseCase {
    override suspend fun invoke(): List<CountryListResponse> =
        repository.getCountryList()
}