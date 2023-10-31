package com.nilanshi.data.repository

import com.nilanshi.data.dto.CountryDto
import com.nilanshi.data.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class CountryDetailsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    CountryDetailsRepository {
    override suspend fun getCountryDetails(code: String): Response<List<CountryDto>> {
        return apiService.getSingleCountryDetails(code)
    }
}
