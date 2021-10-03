package dc.android.devtest.data.repository

import dc.android.devtest.common.Resource
import dc.android.devtest.data.exception.ServerConnectionException
import dc.android.devtest.data.remote.WeatherBitApi
import dc.android.devtest.data.remote.model.toWeatherDetailsList
import dc.android.devtest.domain.model.WeatherDetails
import dc.android.devtest.domain.repository.WeatherRepository
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherBitApi: WeatherBitApi
) : WeatherRepository {

    override suspend fun getWeathersByCityId(
        id: String,
        language: String
    ): Resource<List<WeatherDetails>> {
        return try {
            val response = weatherBitApi.getWeatherByCityId(id = id, language = language)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it.toWeatherDetailsList())
                } ?: Resource.Error(Exception("Unknown Error"))

            } else {
                Resource.Error(Exception("Unknown Error"))
            }
        } catch (e: IOException) {
            Resource.Error(ServerConnectionException(e))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}