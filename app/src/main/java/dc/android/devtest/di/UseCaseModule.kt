package dc.android.devtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dc.android.devtest.domain.repository.CityRepository
import dc.android.devtest.domain.repository.WeatherRepository
import dc.android.devtest.domain.use_case.GetCitiesUseCase
import dc.android.devtest.domain.use_case.GetWeatherByIdUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetWeatherByIdUseCase(weatherRepository: WeatherRepository) =
        GetWeatherByIdUseCase(weatherRepository)

    @Singleton
    @Provides
    fun provideGetGetCitiesUseCase(cityRepository: CityRepository) =
        GetCitiesUseCase(cityRepository)

}