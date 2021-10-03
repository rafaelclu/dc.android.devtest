package dc.android.devtest.data.local.model

import com.google.gson.annotations.SerializedName
import dc.android.devtest.domain.model.City

data class CityData(
    @SerializedName("city_id") val cityId: Int,
    @SerializedName("city_name") val cityName: String,
    @SerializedName("state_code") val state: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("country_full") val countryFull: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)

fun CityData.toCity() = City(
    id = cityId.toString(),
    name = cityName,
    state = state,
    country = countryFull,
    lat = lat,
    lon = lon
)