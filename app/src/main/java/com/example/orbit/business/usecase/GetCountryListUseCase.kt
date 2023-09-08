package com.example.orbit.business.usecase

import com.example.orbit.data.repository.ICountryRepository
import com.example.orbit.data.response.CountryListResponse
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