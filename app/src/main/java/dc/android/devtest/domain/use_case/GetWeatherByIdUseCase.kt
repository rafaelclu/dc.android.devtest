package dc.android.devtest.domain.use_case

import dc.android.devtest.common.Resource
import dc.android.devtest.domain.model.WeatherDetails
import dc.android.devtest.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByIdUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(cityId: String, language: String): Resource<List<WeatherDetails>> =
        weatherRepository.getWeathersByCityId(cityId, language)

}