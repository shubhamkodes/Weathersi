package com.itsthetom.weathersi.repository

import com.itsthetom.weathersi.models.WeatherContainer
import com.itsthetom.weathersi.util.Constant
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object repo {


     private val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)


    suspend fun fetchWeatherData(lat:Double,lon:Double):Response<WeatherContainer>{
        return api.fetchWeatherData(lat,lon,"minutely",Constant.API_KEY)
    }

    suspend fun getCityName(lat:Double,lon:Double):Response<WeatherContainer>{
        return api.getCityName(lat,lon,Constant.API_KEY);
    }

}