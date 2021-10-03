package dc.android.devtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dc.android.devtest.data.local.JsonCityDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideJsonCityDataSource(@ApplicationContext context: Context) =
        JsonCityDataSource(context = context)

}