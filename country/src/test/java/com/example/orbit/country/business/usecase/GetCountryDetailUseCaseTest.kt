package com.example.orbit.country.business.usecase

import com.example.orbit.country.data.repository.ICountryRepository
import com.example.orbit.country.data.response.CountryDetailResponse
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

class GetCountryDetailUseCaseTest {

    private lateinit var useCase: GetCountryDetailUseCase

    @MockK
    lateinit var repository: ICountryRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetCountryDetailUseCase(
            repository = repository
        )
    }

    @After
    fun tearDown() {
        confirmVerified(repository)
    }

    @Test
    fun getCountryList_isSuccess() = runTest {
        // mock
        val result = CountryDetailResponse()
        coEvery { useCase("code") } returns result

        // action
        useCase("code") shouldBe result

        // verify
        coVerify { useCase("code") }
    }

    @Test
    fun getCountryList_isError() = runTest {
        // mock
        val error = RuntimeException()
        coEvery { useCase("code") } throws error

        // action & verify
        coInvoking { useCase("code") } shouldThrow error
        coVerify { useCase("code") }
    }

}