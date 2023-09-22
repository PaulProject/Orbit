package com.example.orbit.country.view.list

import com.example.orbit.country.business.usecase.IGetCountryListUseCase
import com.example.orbit.country.data.response.CountryListResponse
import com.example.orbit.country.navigation.CountryStackScreen
import com.example.orbit.country.view.list.converter.ICountryListConverter
import com.example.orbit.country.view.list.item.CountryListItem
import com.github.terrakok.cicerone.Router
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.excludeRecords
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.mockkObject
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
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

    @MockK
    lateinit var router: Router

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkObject(CountryStackScreen)
        viewModel = CountryListViewModel(
            getCountryList = getCountryList,
            countryListConverter = countryListConverter,
            router = router
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            getCountryList,
            countryListConverter,
            router
        )
    }

    @Test
    fun getCountryList_isSuccess() = runTest {
        // mock
        val state = CountryListState()
        val response = listOf(
            CountryListResponse()
        )
        val result = listOf(
            CountryListItem()
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
    fun getCountryList_isError() = runTest {
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
    fun onCountryClick_whenCode_isNotNull() = runTest {
        // mock
        val state = CountryListState()
        val item = CountryListItem(
            name = "name",
            image = "image",
            code = "code"
        )
        justRun { router.navigateTo(CountryStackScreen.CountryDetailScreen("code", "name")) }

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onCountryClick(item) }

        // verify
        verify { router.navigateTo(CountryStackScreen.CountryDetailScreen("code", "name")) }
    }

    @Test
    fun onCountryClick_whenCode_isNull() = runTest {
        // mock
        val state = CountryListState()
        val item = CountryListItem(
            name = "name",
            image = "image",
        )

        // action & verify
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onCountryClick(item) }
    }

    @Test
    fun onRetry() = runTest {
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