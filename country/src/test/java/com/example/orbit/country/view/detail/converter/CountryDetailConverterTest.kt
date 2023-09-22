package com.example.orbit.country.view.detail.converter

import com.example.orbit.country.data.response.CountryDetailResponse
import com.example.orbit.country.view.detail.item.CountryDetailItem
import org.amshove.kluent.shouldBeEqualTo

import org.junit.Before
import org.junit.Test

class CountryDetailConverterTest {

    private lateinit var converter: CountryDetailConverter

    @Before
    fun setUp() {
        converter = CountryDetailConverter()
    }

    @Test
    fun convert() {
        // mock
        val response = CountryDetailResponse()
        val item = CountryDetailItem()

        // action
        val result = converter.convert(response)

        // verify
        result shouldBeEqualTo item
    }
}