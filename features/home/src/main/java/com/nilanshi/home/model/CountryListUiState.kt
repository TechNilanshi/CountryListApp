package com.nilanshi.home.model

import com.nilanshi.common.base.UiState
import com.nilanshi.data.model.CountryListModel
import javax.annotation.concurrent.Immutable

@Immutable
data class CountryListUiState(
    val isLoading: Boolean,
    val countries: List<CountryListModel> = emptyList(),
    val errorCode: Int = 0,
) : UiState {

    companion object {
        fun initial() = CountryListUiState(
            isLoading = true,
        )
    }
}