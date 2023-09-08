package com.example.orbit.view.detail

import androidx.lifecycle.SavedStateHandle
import com.example.orbit.business.usecase.IGetCountryDetailUseCase
import com.example.orbit.data.response.CountryDetailResponse
import com.example.orbit.data.response.CountryListResponse
import com.example.orbit.view.detail.arg.CountryDetailArg
import com.example.orbit.view.detail.converter.ICountryDetailConverter
import com.example.orbit.view.detail.item.CountryDetailItem
import com.example.orbit.view.list.CountryListState
import com.example.orbit.view.list.item.CountryListItem
import com.github.terrakok.cicerone.Router
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.orbitmvi.orbit.test

class CountryDetailViewModelTest {

    private lateinit var viewModel: CountryDetailViewModel

    @MockK
    lateinit var getCountryDetail: IGetCountryDetailUseCase

    @MockK
    lateinit var countryDetailConverter: ICountryDetailConverter

    @MockK
    lateinit var router: Router

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CountryDetailViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf(
                    CountryDetailViewModel.ARG_COUNTRY_DETAIL to CountryDetailArg(
                        code = "code",
                        name = "name"
                    )
                )
            ),
            getCountryDetail = getCountryDetail,
            countryDetailConverter = countryDetailConverter,
            router = router
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            getCountryDetail,
            countryDetailConverter,
            router,
        )
    }

    @Test
    fun getCountryDetail_isSuccess() = runTest {
        // mock
        val state = CountryDetailState(
            name = "name"
        )
        val response = CountryDetailResponse()
        val result = CountryDetailItem()
        coEvery { getCountryDetail("code") } returns response
        every { countryDetailConverter.convert(response) } returns result

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onViewReady() }

        // verify
        coVerify { getCountryDetail("code") }
        verify { countryDetailConverter.convert(response) }
        testSubject.assert(state) {
            states(
                { copy(isProgress = true) },
                { copy(item = result) },
                { copy(isProgress = false) },
            )
        }
    }

    @Test
    fun getCountryDetail_isError() = runTest {
        // mock
        val state = CountryDetailState(
            name = "name"
        )
        val error = RuntimeException()
        coEvery { getCountryDetail("code") } throws error

        // action
        val testSubject = viewModel.test(state)
        testSubject.testIntent { onViewReady() }

        // verify
        coVerify { getCountryDetail("code") }
        testSubject.assert(state) {
            states(
                { copy(isProgress = true) },
                { copy(error = error) },
                { copy(isProgress = false) },
            )
        }
    }

    @Test
    fun onBackClick() {
        // mock
        justRun { router.exit() }

        // action
        viewModel.onBackClick()

        // verify
        verify { router.exit() }
    }
}