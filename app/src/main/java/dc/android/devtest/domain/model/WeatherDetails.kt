package dc.android.devtest.domain.model

import java.util.*

data class WeatherDetails(
    val cityName: String,
    val timezone: String,
    val forecastDate: Calendar,
    val windSpeed: Double,
    val windDirection: String,
    val temperature: Double,
    val maxTemp: Double,
    val minTemp: Double,
    val apparentMaxTemp: Double,
    val apparentMinTemp: Double,
    val precipitationPercentage: Double,
    val precipitationVolume: Double,
    val humidity: Double,
    val weatherIcon: String,
    val weatherDescription: String,
)
