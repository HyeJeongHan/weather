package com.hjhan.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjhan.weather.data.CityItem
import com.hjhan.weather.data.WeatherResponse
import com.hjhan.weather.repository.CityRepository
import com.hjhan.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _cityData = MediatorLiveData<List<CityItem>>()
    val cityData: LiveData<List<CityItem>>
        get() = _cityData

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val originList = mutableListOf<CityItem>()

    val searchKeyword = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    fun getCityList() {
        originList.addAll(cityRepository.getCityList())
        _cityData.postValue(originList)
    }

    fun searchCityList() {
        _showProgress.value = true
        searchKeyword.value?.let { keyword ->
            if (keyword.isBlank()) {
                _cityData.value = (originList)
            } else {
                val filteredList = originList.filter {
                    it.name.contains(keyword)
                }
                _cityData.value = (filteredList)
            }
        } ?: run {
            _cityData.value = (originList)
        }
    }

    fun hideProgress() {
        _showProgress.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}