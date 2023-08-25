package com.example.orbit.view

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.orbit.business.usecase.IGetCountryListUseCase
import com.example.orbit.extension.RequestAction
import com.example.orbit.extension.createContainer
import com.example.orbit.extension.request
import com.example.orbit.view.converter.ICountryListConverter
import com.example.orbit.view.item.CountryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountryList: IGetCountryListUseCase,
    private val countryListConverter: ICountryListConverter,
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

    fun onCountryClick(item: CountryItem) = intent {
        postSideEffect(CountryListSideEffect.ShowNameOfCountryDialog(item.name))
    }

    fun onRetry() = intent {
        onViewReady()
    }

}