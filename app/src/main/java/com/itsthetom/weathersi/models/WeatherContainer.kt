package com.itsthetom.weathersi.models

data class WeatherContainer(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val id:Int,
    val name:String
)