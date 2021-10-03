package dc.android.devtest.domain.model

import dc.android.devtest.presentation.weather_detail.model.WeatherDetailsModel
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

fun WeatherDetails.toWeatherDetailsModel() = WeatherDetailsModel(
    cityName = cityName,
    timezone = timezone,
    forecastDate = forecastDate,
    temperature = "$temperature°C",
    minTemp = "$minTemp°",
    maxTemp = "$maxTemp°",
    apparentTemp = "$apparentMinTemp° - $apparentMaxTemp°",
    wind = "${String.format("%.2f", windSpeed)}m/s - $windDirection",
    precipitation = "${precipitationVolume}mm - ${precipitationPercentage}%",
    humidity = "${humidity}%",
    weatherIcon = weatherIcon,
    weatherDescription = weatherDescription
)
