package com.itsthetom.weathersi.repository

import com.itsthetom.weathersi.models.WeatherContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api{

    //https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=hourly,daily&appid={API key}

    @GET("data/2.5/onecall")
    suspend fun fetchWeatherData(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("exclude") exclude:String,
        @Query("appid") apiKey:String
    ):Response<WeatherContainer>


    //https://api.openweathermap.org/data/2.5/weather?lat=28.9558184&lon=77.7392163&appid=c5d5a63fa24baa38422b80ce49e1119d
   @GET("data/2.5/weather")
    suspend fun getCityName(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") apiKey:String
    ):Response<WeatherContainer>
}