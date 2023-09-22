package com.example.orbit.country.business.usecase

import com.example.orbit.country.data.repository.ICountryRepository
import com.example.orbit.country.data.response.CountryDetailResponse
import javax.inject.Inject

interface IGetCountryDetailUseCase {
    suspend operator fun invoke(code: String): CountryDetailResponse
}

class GetCountryDetailUseCase @Inject constructor(
    private val repository: ICountryRepository
) : IGetCountryDetailUseCase {
    override suspend fun invoke(code: String): CountryDetailResponse =
        repository.getCountryDetailByCode(code)
}