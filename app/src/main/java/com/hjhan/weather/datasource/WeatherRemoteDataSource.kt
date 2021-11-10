package com.hjhan.weather.datasource

import com.hjhan.weather.data.WeatherResponse
import com.hjhan.weather.remote.RemoteClient.WEATHER_APP_ID
import com.hjhan.weather.remote.WeatherApiService
import io.reactivex.Single
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApiService
) : WeatherRemoteDataSource {

    override fun getWeatherOfCity(cityId: Long): Single<WeatherResponse> {
        return weatherApi.getWeatherOfCity(cityId, WEATHER_APP_ID)
    }
}

interface WeatherRemoteDataSource {
    fun getWeatherOfCity(cityId: Long): Single<WeatherResponse>
}
