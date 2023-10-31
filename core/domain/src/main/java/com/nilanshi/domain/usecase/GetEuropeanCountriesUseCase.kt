package com.nilanshi.domain.usecase

import com.nilanshi.data.dto.CountryDto
import com.nilanshi.data.model.CountryListModel
import com.nilanshi.data.repository.EuropeanCountriesListRepository
import com.nilanshi.data.util.AppConstants
import com.nilanshi.domain.mapper.EuropeanCountryListMapper
import com.nilanshi.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEuropeanCountriesUseCase @Inject constructor(private val europeCountriesListRepository: EuropeanCountriesListRepository) {
    suspend operator fun invoke(): Flow<Response<List<CountryListModel>>> =
        flow {
            val response = europeCountriesListRepository.getEuropeCountriesList()
            if (response.isSuccessful) {
                val countriesList = response.body() as List<CountryDto>
                val result = EuropeanCountryListMapper().mapFrom(countriesList)
                emit(Response.Success(result))
            } else {
                emit(
                    Response.Error(
                        code = AppConstants.API_RESPONSE_ERROR,
                        message = response.message()
                    )
                )
            }
        }
}
