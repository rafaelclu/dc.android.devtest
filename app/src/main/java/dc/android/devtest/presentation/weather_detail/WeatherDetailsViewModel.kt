package dc.android.devtest.presentation.weather_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.toWeatherDetailsModel
import dc.android.devtest.domain.use_case.GetWeatherByIdUseCase
import dc.android.devtest.presentation.weather_detail.model.WeatherDetailsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailsUiState {
    data class State(val data: WeatherDetailsModel) : DetailsUiState()
    data class Error(val exception: Exception) : DetailsUiState()
    object Loading : DetailsUiState()
}

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherByIdUseCase: GetWeatherByIdUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getWeather(cityId: String, language: String) {
        viewModelScope.launch {
            when (val result = getWeatherByIdUseCase(cityId, language)) {
                is Resource.Error -> {
                    _uiState.value = DetailsUiState.Error(result.exception)
                }
                is Resource.Success -> {
                    val tomorrowWeather = result.data[1]
                    _uiState.value = DetailsUiState.State(tomorrowWeather.toWeatherDetailsModel())
                }
            }
        }
    }
}