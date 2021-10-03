package dc.android.devtest.data.remote

import dc.android.devtest.BuildConfig
import dc.android.devtest.data.remote.model.WeatherDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherBitApi {

    @GET("forecast/daily")
    suspend fun getWeatherByCityId(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("city_id") id: String,
        @Query("lang") language: String,
        @Query("days") days: Int = 2
    ): Response<WeatherDetailsResponse>

}