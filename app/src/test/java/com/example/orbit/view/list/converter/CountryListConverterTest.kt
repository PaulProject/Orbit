package com.example.orbit.view.list.converter

import com.example.orbit.data.response.CountryListResponse
import com.example.orbit.view.list.item.CountryListItem
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContainAll
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CountryListConverterTest {

    private lateinit var converter: CountryListConverter

    @Before
    fun setUp() {
        converter = CountryListConverter()
    }

    @Test
    fun convert() {
        // mock
        val response = listOf(CountryListResponse())
        val item = listOf(CountryListItem())

        // action
        val result = converter.convert(response)

        // verify
        result shouldBeEqualTo item
    }
}