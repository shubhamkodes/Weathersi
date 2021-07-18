package com.itsthetom.weathersi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsthetom.weathersi.models.Current
import com.itsthetom.weathersi.models.Hourly
import com.itsthetom.weathersi.models.Weather
import com.itsthetom.weathersi.models.WeatherContainer
import com.itsthetom.weathersi.repository.repo
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel:ViewModel() {

    private val weatherContainer=MutableLiveData<WeatherContainer>()
    private val hourlyData=MutableLiveData<List<Hourly>>()
    private val currentWeather= MutableLiveData<Current>()
    private val cityName=MutableLiveData<String>()
    fun fetchWeatherData(lat:Double,lon:Double){
        viewModelScope.launch {
            var apiResponse=repo.fetchWeatherData(lat,lon)
            if (apiResponse.isSuccessful){
                apiResponse.body()!!.let { it->
                    weatherContainer.postValue(it)
                    Log.d("Tag","view model "+it.name+" "+it.timezone+" "+ it.id)
                    hourlyData.postValue(it.hourly)
                    currentWeather.postValue(it.current)
                }
            }else{
                Log.d("TAG",apiResponse.errorBody()!!.string())
            }
            apiResponse=repo.getCityName(lat,lon)
            if (apiResponse.isSuccessful){
                if (apiResponse.body()!=null){
                    apiResponse.body().let {
                        cityName.postValue(it!!.name)
                    }
                }
            }else{
                Log.d("TAG",apiResponse.errorBody()!!.string())
            }
        }
    }


    fun getHourlyWeather():LiveData<List<Hourly>>{
        return hourlyData
    }
    fun getCurrentWeaterData():LiveData<Current>{
        return currentWeather
    }

    fun getCityName():LiveData<String>{
        return cityName
    }




}