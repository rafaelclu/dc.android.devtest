package dc.android.devtest.domain.repository

import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.WeatherDetails

interface WeatherRepository {

    suspend fun getWeathersByCityId(id: String, language:String): Resource<List<WeatherDetails>>

}