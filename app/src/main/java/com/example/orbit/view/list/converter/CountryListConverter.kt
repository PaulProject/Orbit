package com.example.orbit.view.list.converter

import com.example.orbit.data.response.CountryListResponse
import com.example.orbit.view.list.item.CountryListItem
import javax.inject.Inject

interface ICountryListConverter {
    fun convert(list: List<CountryListResponse>): List<CountryListItem>
}

class CountryListConverter @Inject constructor() : ICountryListConverter {
    override fun convert(list: List<CountryListResponse>): List<CountryListItem> =
        list.map { country ->
            CountryListItem(
                name = country.name?.common.orEmpty(),
                image = country.flags?.png.orEmpty(),
                code = country.cca2
            )
        }

}