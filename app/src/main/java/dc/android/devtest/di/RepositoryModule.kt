package dc.android.devtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dc.android.devtest.data.remote.WeatherBitApi
import dc.android.devtest.data.repository.WeatherRepositoryImpl
import dc.android.devtest.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherBitApi: WeatherBitApi): WeatherRepository =
        WeatherRepositoryImpl(weatherBitApi)

}