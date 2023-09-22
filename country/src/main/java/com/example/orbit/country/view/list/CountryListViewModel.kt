package com.example.orbit.country.view.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.example.orbit.core.extension.RequestAction
import com.example.orbit.core.extension.createContainer
import com.example.orbit.core.extension.request
import com.example.orbit.country.business.usecase.IGetCountryListUseCase
import com.example.orbit.country.navigation.CountryStackScreen
import com.example.orbit.country.view.list.converter.ICountryListConverter
import com.example.orbit.country.view.list.item.CountryListItem
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountryList: IGetCountryListUseCase,
    private val countryListConverter: ICountryListConverter,
    private val router: Router,
) : ViewModel(), ContainerHost<CountryListState, CountryListSideEffect> {

    override val container: Container<CountryListState, CountryListSideEffect> =
        createContainer(
            initialState = CountryListState(),
        ) {
            onViewReady()
        }

    @VisibleForTesting
    fun onViewReady() = intent {
        request { getCountryList() }.collect { action ->
            when (action) {
                is RequestAction.Data -> {
                    reduce {
                        state.copy(
                            items = countryListConverter.convert(
                                action.data
                            )
                        )
                    }
                }

                is RequestAction.Error -> {
                    reduce { state.copy(error = action.error) }
                }

                is RequestAction.Progress -> {
                    reduce { state.copy(isProgress = action.progress) }
                }
            }
        }
    }

    fun onCountryClick(item: CountryListItem) = intent {
        item.code?.let { code ->
            router.navigateTo(
                CountryStackScreen.CountryDetailScreen(
                    code = code, name = item.name
                )
            )
        }
    }

    fun onRetry() = intent {
        onViewReady()
    }

}