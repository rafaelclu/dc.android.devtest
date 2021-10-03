package dc.android.devtest.presentation.weather_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import dc.android.devtest.data.repository.FakeWeatherRepository
import dc.android.devtest.domain.use_case.GetWeatherByIdUseCase
import dc.android.devtest.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


class WeatherDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val fakeRepository = FakeWeatherRepository()
    private val getWeatherByIdUseCase = GetWeatherByIdUseCase(fakeRepository)

    private val cityId = "8953360"
    private val language = "pt"

    @Test
    fun `Get weather by id returns Success`() = runBlocking {
        val viewModel = WeatherDetailsViewModel(getWeatherByIdUseCase)

        viewModel.getWeather(cityId = cityId, language = language)
        val response = viewModel.uiState.first()

        Truth.assertThat(response is DetailsUiState.State).isTrue()
    }

    @Test
    fun `Get weather by id returns Error`() = runBlocking {
        val viewModel = WeatherDetailsViewModel(getWeatherByIdUseCase)
        fakeRepository.setShouldReturnNetworkError(true)

        viewModel.getWeather(cityId = cityId, language = language)
        val response = viewModel.uiState.first()

        Truth.assertThat(response is DetailsUiState.Error).isTrue()
    }
}