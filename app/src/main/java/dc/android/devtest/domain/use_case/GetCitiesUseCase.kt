package dc.android.devtest.domain.use_case

import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.City
import dc.android.devtest.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    operator fun invoke(): Flow<Resource<List<City>>> = cityRepository.getCities()

}