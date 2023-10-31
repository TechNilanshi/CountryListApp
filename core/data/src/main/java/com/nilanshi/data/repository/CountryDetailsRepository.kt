package com.nilanshi.data.repository

import com.nilanshi.data.dto.CountryDto
import retrofit2.Response

interface CountryDetailsRepository {
    suspend fun getCountryDetails(code: String): Response<List<CountryDto>>
}
