package com.example.orbit.country.view.detail

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.orbit.core.extension.RequestAction
import com.example.orbit.core.extension.createContainer
import com.example.orbit.core.extension.request
import com.example.orbit.country.business.usecase.IGetCountryDetailUseCase
import com.example.orbit.country.view.detail.arg.CountryDetailArg
import com.example.orbit.country.view.detail.converter.ICountryDetailConverter
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCountryDetail: IGetCountryDetailUseCase,
    private val countryDetailConverter: ICountryDetailConverter,
    private val router: Router,
) : ViewModel(), ContainerHost<CountryDetailState, Nothing> {

    companion object {
        const val ARG_COUNTRY_DETAIL = "arg_country_detail"
    }

    private val arg: CountryDetailArg by lazy {
        requireNotNull(savedStateHandle[ARG_COUNTRY_DETAIL])
    }

    override val container: Container<CountryDetailState, Nothing> =
        createContainer(
            initialState = CountryDetailState(
                name = arg.name
            ),
        ) {
            onViewReady()
        }

    @VisibleForTesting
    fun onViewReady() = intent {
        request { getCountryDetail(arg.code) }
            .collect { action ->
                when (action) {
                    is RequestAction.Data -> reduce {
                        state.copy(
                            item = countryDetailConverter.convert(
                                action.data
                            )
                        )
                    }

                    is RequestAction.Error -> reduce {
                        state.copy(error = action.error)
                    }

                    is RequestAction.Progress -> reduce {
                        state.copy(isProgress = action.progress)
                    }
                }
            }
    }

    fun onBackClick() {
        router.exit()
    }

}