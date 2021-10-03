package dc.android.devtest.data.repository

import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.WeatherDetails
import dc.android.devtest.domain.repository.WeatherRepository
import dc.android.devtest.util.weathersDummyList

class FakeWeatherRepository : WeatherRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getWeathersByCityId(
        id: String,
        language: String
    ): Resource<List<WeatherDetails>> = if (shouldReturnNetworkError) {
        Resource.Error(Exception("Error"))
    } else {
        Resource.Success(weathersDummyList)
    }
}