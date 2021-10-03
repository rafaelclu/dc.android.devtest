package dc.android.devtest.data.repository

import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.City
import dc.android.devtest.domain.repository.CityRepository
import dc.android.devtest.util.cityDummyList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCityRepository : CityRepository {

    private var shouldReturnError: Boolean = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnError = value
    }

    override fun getCities(): Flow<Resource<List<City>>> = flow {
        emit(
            if (shouldReturnError)
                Resource.Error(Exception("Error"))
            else
                Resource.Success(cityDummyList)
        )
    }

}