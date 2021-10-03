package dc.android.devtest.data.repository

import com.google.common.truth.Truth.assertThat
import dc.android.devtest.common.Resource
import dc.android.devtest.data.remote.WeatherBitApi
import dc.android.devtest.util.networkResponseWithFileContent
import dc.android.devtest.util.weathersDummyList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherRepositoryImplTest {
    private val mockWebServer: MockWebServer = MockWebServer()

    private val cityId = "8953360"
    private val language = "pt"

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherBitApi::class.java)

    private val repository = WeatherRepositoryImpl(api)

    @Test
    fun `Get weather by id returns Success`() {
        mockWebServer.networkResponseWithFileContent("success_weather_response_list.json", 200)

        runBlocking {
            val response = repository.getWeathersByCityId(id = cityId, language = language)

            assertThat(response is Resource.Success).isTrue()
        }
    }

    @Test
    fun `Get weather by id returns expected value`() {
        mockWebServer.networkResponseWithFileContent("success_weather_response_list.json", 200)

        runBlocking {
            val response = repository.getWeathersByCityId(id = cityId, language = language)
            val data = (response as Resource.Success).data

            val expected = weathersDummyList

            assertThat(data).isEqualTo(expected)
        }
    }

    @Test
    fun `Get weather by id returns error`() {
        mockWebServer.networkResponseWithFileContent("success_weather_response_list.json", 500)

        runBlocking {
            val response = repository.getWeathersByCityId(id = cityId, language = language)

            assertThat(response is Resource.Error).isTrue()
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}