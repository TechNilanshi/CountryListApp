package com.nilanshi.home.intent

import com.nilanshi.common.base.UiEvent
import com.nilanshi.data.model.CountryListModel
import javax.annotation.concurrent.Immutable

@Immutable
sealed class CountryListScreenUiEvent : UiEvent {
    data class ShowData(val data: List<CountryListModel>) : CountryListScreenUiEvent()
    object OnAPIError : CountryListScreenUiEvent()
    object OnInternetError : CountryListScreenUiEvent()
    data class IsLoading(val isLoading: Boolean) : CountryListScreenUiEvent()

}