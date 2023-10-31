package com.nilanshi.domain.di

import com.nilanshi.data.repository.CountryDetailsRepository
import com.nilanshi.data.repository.EuropeanCountriesListRepository
import com.nilanshi.domain.usecase.GetCountriesDetailsUseCase
import com.nilanshi.domain.usecase.GetEuropeanCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideGetEuropeanCountriesUseCase(europeanCountriesListRepository: EuropeanCountriesListRepository): GetEuropeanCountriesUseCase {
        return GetEuropeanCountriesUseCase(europeanCountriesListRepository)
    }

    @Provides
    fun provideGetCountryInformationUseCase(
        countryDetailsRepository: CountryDetailsRepository
    ): GetCountriesDetailsUseCase {
        return GetCountriesDetailsUseCase(
            countryDetailsRepository = countryDetailsRepository
        )
    }
}