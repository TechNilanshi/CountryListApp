package com.nilanshi.data.repository

import com.nilanshi.data.dto.CountryDto
import retrofit2.Response

interface EuropeanCountriesListRepository {

    suspend fun getEuropeCountriesList(): Response<List<CountryDto>>
}
