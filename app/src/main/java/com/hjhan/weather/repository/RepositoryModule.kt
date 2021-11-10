package com.hjhan.weather.repository

import android.content.Context
import android.content.res.AssetManager
import com.hjhan.weather.datasource.*
import com.hjhan.weather.remote.RemoteClient
import com.hjhan.weather.remote.WeatherApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    abstract fun provideCityRepository(repositoryImpl: CityRepositoryImpl): CityRepository

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideWeatherRemoteDataSource(dataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideCityDataSource(dataSourceImpl: CityDataSourceImpl): CityDataSource

}

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideShopService(): WeatherApiService {
        return RemoteClient.getApi()
    }

    @Singleton
    @Provides
    fun provideGoodsLikeService(@ApplicationContext context: Context): AssetManager {
        return context.assets
    }
}