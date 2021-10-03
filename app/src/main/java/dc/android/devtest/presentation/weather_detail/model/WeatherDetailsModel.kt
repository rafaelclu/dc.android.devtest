package dc.android.devtest.presentation.weather_detail.model

import java.util.*

data class WeatherDetailsModel(
    val cityName: String = "",
    val timezone: String = "",
    val forecastDate: Calendar = Calendar.getInstance(),
    val temperature: String = "",
    val minTemp: String = "",
    val maxTemp: String = "",
    val apparentTemp: String = "",
    val wind: String = "",
    val precipitation: String = "",
    val humidity: String = "",
    val weatherIcon: String = "",
    val weatherDescription: String = ""
)
