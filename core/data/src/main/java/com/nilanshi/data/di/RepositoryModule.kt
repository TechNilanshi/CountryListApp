package com.nilanshi.data.di

import com.nilanshi.data.repository.CountryDetailsRepository
import com.nilanshi.data.repository.CountryDetailsRepositoryImpl
import com.nilanshi.data.repository.EuropeanCountriesListRepository
import com.nilanshi.data.repository.EuropeanCountriesListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCountryListRepository(
        europeCountriesListRepositoryImpl: EuropeanCountriesListRepositoryImpl,
    ): EuropeanCountriesListRepository

    @Binds
    abstract fun bindCountryDetailsRepository(
        countryDetailsRepositoryImpl: CountryDetailsRepositoryImpl,
    ): CountryDetailsRepository
}
