package dc.android.devtest.data.repository

import com.google.gson.JsonSyntaxException
import dc.android.devtest.common.Resource
import dc.android.devtest.data.exception.JsonReadException
import dc.android.devtest.data.local.JsonCityDataSource
import dc.android.devtest.data.local.model.CityData
import dc.android.devtest.data.local.model.toCity
import dc.android.devtest.domain.model.City
import dc.android.devtest.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val jsonCityDataSource: JsonCityDataSource
) : CityRepository {

    override fun getCities(): Flow<Resource<List<City>>> =
        jsonCityDataSource.getCities().map { cityDataList ->
            try {
                Resource.Success(cityDataList.map(CityData::toCity))
            } catch (e: IOException) {
                Resource.Error(JsonReadException(e))
            } catch (e: JsonSyntaxException) {
                Resource.Error(e)
            }
        }
}