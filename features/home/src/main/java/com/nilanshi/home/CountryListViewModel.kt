package com.nilanshi.home

import androidx.lifecycle.viewModelScope
import com.nilanshi.common.base.BaseViewModel
import com.nilanshi.common.base.Reducer
import com.nilanshi.common.util.NetworkHelper
import com.nilanshi.data.util.AppConstants
import com.nilanshi.domain.usecase.GetEuropeanCountriesUseCase
import com.nilanshi.domain.util.Response
import com.nilanshi.home.intent.CountryListScreenUiEvent
import com.nilanshi.home.model.CountryListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is responsible for pass countries list to composable functions
 * @param getEuropeanCountries
 * @param networkHelper
 */
@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getEuropeanCountries: GetEuropeanCountriesUseCase,
    private val networkHelper: NetworkHelper,
) : BaseViewModel<CountryListUiState, CountryListScreenUiEvent>() {

    private val reducer = MainReducer(CountryListUiState.initial())

    override val state: StateFlow<CountryListUiState>
        get() = reducer.state

    private fun sendEvent(event: CountryListScreenUiEvent) {
        reducer.sendEvent(event)
    }

    init {
        loadCountries()
    }

    fun loadCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            if (networkHelper.isNetworkConnected()) {
                getEuropeanCountries()
                    .collect() { response ->
                        when (response) {
                            is Response.Success -> {
                                sendEvent(CountryListScreenUiEvent.ShowData(data = response.data))
                            }

                            is Response.Error -> {
                                sendEvent(CountryListScreenUiEvent.OnAPIError)
                            }

                            is Response.Exception -> {
                                sendEvent(CountryListScreenUiEvent.OnAPIError)
                            }
                        }
                    }
            } else {
                sendEvent(CountryListScreenUiEvent.OnInternetError)
            }
        }
    }

    private class MainReducer(initial: CountryListUiState) :
        Reducer<CountryListUiState, CountryListScreenUiEvent>(initial) {
        override fun reduce(oldState: CountryListUiState, event: CountryListScreenUiEvent) {
            when (event) {
                is CountryListScreenUiEvent.ShowData -> {
                    setState(oldState.copy(countries = event.data, isLoading = false))
                }

                is CountryListScreenUiEvent.IsLoading -> {
                    setState(oldState.copy(isLoading = event.isLoading))
                }

                is CountryListScreenUiEvent.OnAPIError -> {
                    setState(oldState.copy(errorCode = AppConstants.API_RESPONSE_ERROR, isLoading = false))
                }

                is CountryListScreenUiEvent.OnInternetError -> {
                    setState(oldState.copy(errorCode = AppConstants.INTERNET_ERROR, isLoading = false))
                }
            }
        }
    }
}