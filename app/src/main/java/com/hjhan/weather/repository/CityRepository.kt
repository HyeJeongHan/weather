package com.hjhan.weather.repository

import com.hjhan.weather.data.CityItem
import com.hjhan.weather.datasource.CityDataSource
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityDataSource: CityDataSource
) : CityRepository {
    override fun getCityList(): List<CityItem> {
        return cityDataSource.getCityList()
    }
}

interface CityRepository {
    fun getCityList(): List<CityItem>
}