package com.hjhan.weather.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse (
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long
) {
    fun cityNameStr() = "도시 이름 : $name"
}

data class Clouds (
    val all: Long
)

data class Main (
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,

    val pressure: Long,
    val humidity: Long,

    @SerializedName("sea_level")
    val seaLevel: Long,

    @SerializedName("grnd_level")
    val grndLevel: Long
) {
    fun tempMinStr() = "최저 온도 : $tempMin"
    fun tempMaxStr() = "최고 온도 : $tempMax"
}

data class Sys (
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Wind (
    val speed: Double,
    val deg: Long,
    val gust: Double
)
