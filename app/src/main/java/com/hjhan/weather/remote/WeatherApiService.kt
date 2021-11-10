package com.hjhan.weather.remote

import com.hjhan.weather.data.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    fun getWeatherOfCity(
        @Query("id") cityId: Long,
        @Query("appid") appKey: String
    ): Single<WeatherResponse>
}