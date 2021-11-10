package com.hjhan.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjhan.weather.data.WeatherResponse
import com.hjhan.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeatherOfCityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse>
        get() = _weatherData

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _showErrorToast = MutableLiveData<Unit>()
    val showErrorToast: LiveData<Unit>
        get() = _showErrorToast

    private val compositeDisposable = CompositeDisposable()

    fun getWeatherOfCity(cityId: Long) {
        _showProgress.value = true
        weatherRepository.getWeatherOfCity(cityId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                _weatherData.postValue(response)
                _showProgress.value = false
            }, { error ->
                error.printStackTrace()
                _showErrorToast.postValue(Unit)
                _showProgress.value = false
            }).also {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}