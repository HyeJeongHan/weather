package com.hjhan.weather.repository

import com.hjhan.weather.data.WeatherResponse
import com.hjhan.weather.datasource.WeatherRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {
    override fun getWeatherOfCity(cityId: Long): Single<WeatherResponse> {
        return weatherRemoteDataSource.getWeatherOfCity(cityId)
    }
}

interface WeatherRepository {
    fun getWeatherOfCity(cityId: Long): Single<WeatherResponse>
}