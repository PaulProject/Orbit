package com.example.orbit.business.usecase

import com.example.orbit.data.repository.ICountryRepository
import com.example.orbit.data.response.CountryListResponse
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

class GetCountryListUseCaseTest {

    private lateinit var useCase: GetCountryListUseCase

    @MockK
    lateinit var repository: ICountryRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetCountryListUseCase(
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
        val result = listOf<CountryListResponse>()
        coEvery { useCase() } returns result

        // action
        useCase() shouldBe result

        // verify
        coVerify { useCase() }
    }

    @Test
    fun getCountryList_isError() = runTest {
        // mock
        val error = RuntimeException()
        coEvery { useCase() } throws error

        // action & verify
        coInvoking { useCase() } shouldThrow error
        coVerify { useCase() }
    }

}