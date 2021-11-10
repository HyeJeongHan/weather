package com.hjhan.weather.remote

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object RemoteClient {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val WEATHER_APP_ID = "63434f19519e83d4b581b03fde26fcf5"

    fun getApi(): WeatherApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApiService::class.java)

    private fun provideOkHttpClient(): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        return b.build()
    }
}