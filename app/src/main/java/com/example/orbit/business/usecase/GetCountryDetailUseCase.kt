package com.example.orbit.business.usecase

import com.example.orbit.data.repository.ICountryRepository
import com.example.orbit.data.response.CountryDetailResponse
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