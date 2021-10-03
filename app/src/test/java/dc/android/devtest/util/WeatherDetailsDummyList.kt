package dc.android.devtest.util

import dc.android.devtest.common.extension.toCalendar
import dc.android.devtest.domain.model.WeatherDetails

val weathersDummyList = listOf(
    WeatherDetails(
        cityName = "Comugne",
        timezone = "Europe/Rome",
        forecastDate = "2021-10-01".toCalendar(),
        windSpeed = 1.30375,
        windDirection = "Sudeste",
        temperature = 18.8,
        maxTemp = 22.2,
        minTemp = 13.3,
        apparentMaxTemp = 21.5,
        apparentMinTemp = 13.3,
        precipitationPercentage = 0.0,
        precipitationVolume = 0.0,
        humidity = 53.0,
        weatherIcon = "c02d",
        weatherDescription = "Poucas nuvens",
    ),
    WeatherDetails(
        cityName = "Comugne",
        timezone = "Europe/Rome",
        forecastDate = "2021-10-02".toCalendar(),
        windSpeed = 1.48542,
        windDirection = "Leste-Sudeste",
        temperature = 16.2,
        maxTemp = 21.7,
        minTemp = 11.5,
        apparentMaxTemp = 21.3,
        apparentMinTemp = 11.5,
        precipitationPercentage = 0.0,
        precipitationVolume = 0.0,
        humidity = 69.0,
        weatherIcon = "c03d",
        weatherDescription = "Nuvens quebradas",
    )
)