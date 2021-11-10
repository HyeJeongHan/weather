package com.hjhan.weather.data

data class CityItem(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coord
)

data class Coord (
    val lon: Double,
    val lat: Double
)
