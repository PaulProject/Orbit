package com.example.orbit.view

import com.example.orbit.business.usecase.IGetCountryListUseCase
import com.example.orbit.data.response.CountryResponse
import com.example.orbit.view.converter.ICountryListConverter
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.excludeRecords
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

class CountryListViewModelTest {

    private lateinit var viewModel: CountryListViewModel

    @MockK
    lateinit var getCountryList: IGetCountryListUseCase

    @MockK
    lateinit var countryListConverter: ICountryListConverter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CountryListViewModel(
            getCountryList = getCountryList,
            countryListConverter = countryListConverter
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            getCountryList,
            countryListConverter,
        )
    }

    @Test
    fun onViewReady_isSuccess() = runTest {
        // mock
        val state = CountryListState()
        val response = listOf(
            CountryResponse()
        )
        val result = listOf(
            com.example.orbit.view.item.CountryItem()
        )
        coEvery { getCountryList() } returns response
        every { countryListConverter.convert(response) } returns result

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onViewReady() }

        // verify
        coVerify { getCountryList() }
        verify { countryListConverter.convert(response) }
        testSubject.assert(state) {
            states(
                { copy(isProgress = true) },
                { copy(items = result) },
                { copy(isProgress = false) },
            )
        }
    }

    @Test
    fun onViewReady_isError() = runTest {
        // mock
        val state = CountryListState()
        val error = RuntimeException()
        coEvery { getCountryList() } throws error

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onViewReady() }

        // verify
        coVerify { getCountryList() }
        testSubject.assert(state) {
            states(
                { copy(isProgress = true) },
                { copy(error = error) },
                { copy(isProgress = false) },
            )
        }
    }

    @Test
    fun onCountryClick() = runTest {
        // mock
        val state = CountryListState()
        val item = com.example.orbit.view.item.CountryItem(
            name = "name",
            image = "image"
        )

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onCountryClick(item) }

        // verify
        testSubject.assert(state) {
            postedSideEffects(
                CountryListSideEffect.ShowNameOfCountryDialog("name"),
            )
        }
    }

    @Test
    fun onRetry() = runTest{
        // mock
        val state = CountryListState()
        viewModel = spyk(viewModel) {
            excludeRecords {
                container
                onRetry()
            }
        }

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onRetry() }

        // verify
        verify { viewModel.onViewReady() }
        confirmVerified(viewModel)
    }
}