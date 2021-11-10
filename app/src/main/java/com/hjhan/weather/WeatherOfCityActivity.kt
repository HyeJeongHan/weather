package com.hjhan.weather

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hjhan.weather.databinding.ActivityWeatherOfCityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherOfCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherOfCityBinding
    private val viewModel by viewModels<WeatherOfCityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_weather_of_city
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        init()
    }

    private fun init() {
        setWeatherOfCityObserver()

        val cityId = intent.getLongExtra("city_id", 0)
        viewModel.getWeatherOfCity(cityId)
    }

    private fun setWeatherOfCityObserver() {
        viewModel.showErrorToast.observe(this, {
            Toast.makeText(this, "네트워크 에러", Toast.LENGTH_SHORT).show()
        })
    }

}