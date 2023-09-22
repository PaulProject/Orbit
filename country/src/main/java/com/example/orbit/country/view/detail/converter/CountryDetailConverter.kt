package com.example.orbit.country.view.detail.converter

import com.example.orbit.country.data.response.CountryDetailResponse
import com.example.orbit.country.view.detail.item.CountryDetailItem
import javax.inject.Inject

interface ICountryDetailConverter {
    fun convert(response: CountryDetailResponse): CountryDetailItem
}

class CountryDetailConverter @Inject constructor() : ICountryDetailConverter {
    override fun convert(response: CountryDetailResponse): CountryDetailItem =
        CountryDetailItem(
            name = response.name?.common.orEmpty(),
            flag = response.flags?.png.orEmpty(),
            currency = response.currencies?.values?.firstOrNull()?.name.orEmpty(),
            capital = response.capital?.firstOrNull().orEmpty()
        )
}