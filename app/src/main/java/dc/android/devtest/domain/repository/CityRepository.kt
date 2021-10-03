package dc.android.devtest.domain.repository

import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun getCities(): Flow<Resource<List<City>>>

}