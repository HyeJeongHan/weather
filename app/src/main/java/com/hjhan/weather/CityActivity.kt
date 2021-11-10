package com.hjhan.weather

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hjhan.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val cityAdapter by lazy { CityListAdapter(::goWeatherOfCity) }
    private val viewModel by viewModels<CityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        init()
    }

    private fun init() {
        setCityRecyclerViewAdapter()
        setCityListObserver()
        viewModel.getCityList()
    }

    private fun setCityRecyclerViewAdapter() {
        with(binding.cityRecyclerview) {
            itemAnimator = null
            adapter = cityAdapter
        }
    }

    private fun setCityListObserver() {
        viewModel.cityData.observe(this, {
            cityAdapter.submitList(it) {
                viewModel.hideProgress()
            }
        })
    }

    private fun goWeatherOfCity(cityId: Long) {
        val intent = Intent(this, WeatherOfCityActivity::class.java)
        intent.putExtra("city_id", cityId)
        startActivity(intent)
    }
}