package com.nilanshi.domain.usecase

import com.nilanshi.data.dto.CountryDto
import com.nilanshi.data.repository.CountryDetailsRepository
import com.nilanshi.data.util.AppConstants
import com.nilanshi.domain.TestData
import com.nilanshi.domain.mapper.CountryDetailsMapper
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetCountryInformationUseCaseTest {
    @MockK
    private lateinit var countryDetailsRepository: CountryDetailsRepository

    private lateinit var getCountriesDetailsUseCase: GetCountriesDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCountriesDetailsUseCase =
            GetCountriesDetailsUseCase(countryDetailsRepository)
    }

    @Test
    fun `Should validate and pass countries response success test`() = runTest {
        val response: Response<List<CountryDto>> = TestData.getFinlandCountryResponseRetrofit(false)
        val countryList = response.body() as List<CountryDto>
        val result = CountryDetailsMapper().mapFrom(countryList.get(0))
        // Given
        coEvery { countryDetailsRepository.getCountryDetails("419") } returns response
        // When
        val responseFlow = getCountriesDetailsUseCase("419")
        // Then
        responseFlow.collectLatest { data ->
            if (data is com.nilanshi.domain.util.Response.Success) {
                Assert.assertEquals("Finland", result.name)
            } else {
                Assert.assertTrue(false)
            }
        }
    }

    @Test
    fun `Should validate and pass countries response emptyList success test`() = runTest {
        val response: Response<List<CountryDto>> = Response.success(emptyList())
        // Given
        coEvery { countryDetailsRepository.getCountryDetails("419") } returns response
        // When
        val responseFlow = getCountriesDetailsUseCase("419")
        // Then
        responseFlow.collectLatest { data ->
            if (data is com.nilanshi.domain.util.Response.Error) {
                Assert.assertEquals(AppConstants.API_RESPONSE_ERROR, data.code)
            } else {
                Assert.assertTrue(false)
            }
        }
    }

    @Test
    fun `Should validate and pass country response error test`() = runTest {
        val response: Response<List<CountryDto>> = TestData.getFinlandCountryResponseRetrofit(true)
        // Given
        coEvery { countryDetailsRepository.getCountryDetails("419") } returns response
        // When
        val responseFlow = getCountriesDetailsUseCase("419")
        // Then
        responseFlow.collectLatest { data ->
            if (data is com.nilanshi.domain.util.Response.Error) {
                Assert.assertEquals(AppConstants.API_RESPONSE_ERROR, data.code)
            } else {
                Assert.assertTrue(false)
            }
        }
    }

    @Test
    fun `Should validate and pass country response empty records error test`() = runTest {
        // Given
        val response: Response<List<CountryDto>> = TestData.getFinlandCountryResponseRetrofit(true)
        coEvery { countryDetailsRepository.getCountryDetails("419") } returns response
        // When
        val responseFlow = getCountriesDetailsUseCase("419")
        // Then
        responseFlow.collectLatest { data ->
            if (data is com.nilanshi.domain.util.Response.Error) {
                Assert.assertEquals(AppConstants.API_RESPONSE_ERROR, data.code)
            } else {
                Assert.assertTrue(false)
            }
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}