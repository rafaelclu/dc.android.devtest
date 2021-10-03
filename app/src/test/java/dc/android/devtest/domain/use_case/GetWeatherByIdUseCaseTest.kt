package dc.android.devtest.domain.use_case

import com.google.common.truth.Truth.assertThat
import dc.android.devtest.common.Resource
import dc.android.devtest.data.repository.FakeWeatherRepository
import dc.android.devtest.util.weathersDummyList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetWeatherByIdUseCaseTest{
    private val cityId = "8953360"
    private val language = "pt"

    private val fakeRepository = FakeWeatherRepository()

    @Test
    fun `Get weather by id use case returns Success`() = runBlocking {
        val getWeatherById = GetWeatherByIdUseCase(fakeRepository)

        val response = getWeatherById(cityId = cityId, language = language)

        assertThat(response is Resource.Success).isTrue()
    }

    @Test
    fun `Get weather by id use case returns expected value`() = runBlocking {
        val getWeatherById = GetWeatherByIdUseCase(fakeRepository)

        val response = getWeatherById(cityId = cityId, language = language)
        val data = (response as Resource.Success).data

        assertThat(data).isEqualTo(weathersDummyList)
    }

    @Test
    fun `Get weather by id use case returns error`() = runBlocking {
        val getWeatherById = GetWeatherByIdUseCase(fakeRepository)
        fakeRepository.setShouldReturnNetworkError(true)

        val response = getWeatherById(cityId = cityId, language = language)

        assertThat(response is Resource.Error).isTrue()
    }
}