package com.example.orbit.view.converter

import com.example.orbit.data.response.CountryResponse
import com.example.orbit.view.item.CountryItem
import javax.inject.Inject

interface ICountryListConverter {
    fun convert(list: List<CountryResponse>): List<CountryItem>
}

class CountryListConverter @Inject constructor() : ICountryListConverter {
    override fun convert(list: List<CountryResponse>): List<CountryItem> =
        list.map { country ->
            CountryItem(
                name = country.name.orEmpty(),
                image = country.image.orEmpty()
            )
        }

}