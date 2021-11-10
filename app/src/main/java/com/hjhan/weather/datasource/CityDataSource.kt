package com.hjhan.weather.datasource

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hjhan.weather.data.CityItem
import java.lang.reflect.Type
import javax.inject.Inject

class CityDataSourceImpl @Inject constructor(
    private val asset: AssetManager
) : CityDataSource {

    override fun getCityList(): List<CityItem> {
        val cityJsonString =
            try {
                asset.open("citylist.json").bufferedReader().use { it.readText() }
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        return stringToCityItem(cityJsonString)
    }

    private fun stringToCityItem(json: String): List<CityItem> {
        val type: Type = object : TypeToken<List<CityItem>?>() {}.type
        return Gson().fromJson(json, type)
    }
}

interface CityDataSource {
    fun getCityList(): List<CityItem>
}
