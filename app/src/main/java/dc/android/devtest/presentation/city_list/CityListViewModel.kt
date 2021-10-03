package dc.android.devtest.presentation.city_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.City
import dc.android.devtest.domain.model.toCityModel
import dc.android.devtest.domain.use_case.GetCitiesUseCase
import dc.android.devtest.presentation.city_list.model.CityModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CityListUiState {
    data class State(val data: List<CityModel>) : CityListUiState()
    data class Error(val exception: Exception) : CityListUiState()
    object Loading : CityListUiState()
}

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private var citiesSequence: Sequence<CityModel> = emptySequence()

    private val _uiState =
        MutableStateFlow<CityListUiState>(CityListUiState.State(emptyList()))
    val uiState = _uiState.asStateFlow()

    fun loadCities() {
        viewModelScope.launch {
            getCitiesUseCase().collect { response ->
                when (response) {
                    is Resource.Error -> _uiState.value =
                        CityListUiState.Error(exception = response.exception)
                    is Resource.Success -> {
                        val cities = response.data
                        citiesSequence = cities.map(City::toCityModel).asSequence()
                    }
                }
            }
        }
    }

    fun searchCities(cityName: String) {
        _uiState.value = CityListUiState.Loading

        val searchResponse = citiesSequence
            .filter { city -> city.cityName.uppercase().contains(cityName.uppercase()) }
            .take(10)
            .toList()

        _uiState.value = CityListUiState.State(searchResponse)
    }
}