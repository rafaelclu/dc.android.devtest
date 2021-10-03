package dc.android.devtest.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dc.android.devtest.R
import dc.android.devtest.data.local.model.CityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.BufferedReader
import javax.inject.Inject

class JsonCityDataSource(private val context: Context) {

    fun getCities(): Flow<List<CityData>> =
        readJsonFile().map {
            val cities = Gson().fromJson<List<CityData>>(
                it,
                TypeToken.getParameterized(
                    ArrayList::class.java,
                    CityData::class.java
                ).type
            )
            cities
        }

    private fun readJsonFile(): Flow<String> = flow {
        val inputStream = context.resources.openRawResource(R.raw.brazil_cities)
        val content = inputStream.bufferedReader().use(BufferedReader::readText)
        emit(content)
    }.flowOn(Dispatchers.IO)

}