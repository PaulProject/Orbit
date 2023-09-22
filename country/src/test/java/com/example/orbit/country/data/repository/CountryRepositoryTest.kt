package com.example.orbit.country.data.repository

import com.example.orbit.country.data.response.CountryListResponse
import com.example.orbit.country.data.service.CountryService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.coInvoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldThrow
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountryRepositoryTest {

    private lateinit var repository: CountryRepository

    @MockK
    lateinit var service: CountryService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = CountryRepository(
            service = service
        )
    }

    @After
    fun tearDown() {
        confirmVerified(service)
    }

    @Test
    fun getCountryList_isSuccess() = runTest {
        // mock
        val result = listOf<CountryListResponse>()
        coEvery { repository.getCountryList() } returns result

        // action
        repository.getCountryList() shouldBe result

        // verify
        coVerify { repository.getCountryList() }
    }

    @Test
    fun getCountryList_isError() = runTest {
        // mock
        val error = RuntimeException()
        coEvery { repository.getCountryList() } throws error

        // action & verify
        coInvoking { repository.getCountryList() } shouldThrow error
        coVerify { repository.getCountryList() }
    }
}