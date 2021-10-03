package dc.android.devtest.domain.model

import dc.android.devtest.presentation.city_list.model.CityModel

data class City(
    val id: String,
    val name: String,
    val state: String,
    val country: String,
    val lat: Double,
    val lon: Double
)

fun City.toCityModel() = CityModel(
    cityId = id,
    cityName = name,
    cityState = state,
)
