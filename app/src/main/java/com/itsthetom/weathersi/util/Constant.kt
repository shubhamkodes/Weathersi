package com.itsthetom.weathersi.util

import com.itsthetom.weathersi.R

object Constant {
    public val BASE_URL="https://api.openweathermap.org"
    public val API_KEY="c5d5a63fa24baa38422b80ce49e1119d"

    public val weatherIcon= mapOf<String,Int>(
        Pair("01d", R.drawable.img_01d),
        Pair("01n",R.drawable.img_01n),
        Pair("02d",R.drawable.img_02d),
        Pair("02n",R.drawable.img_02n),
        Pair("03d",R.drawable.img_03d),
        Pair("03n",R.drawable.img_03n),
        Pair("04d",R.drawable.img_04d),
        Pair("04n",R.drawable.img_04n),
        Pair("09d",R.drawable.img_09d),
        Pair("09n",R.drawable.img_09n),
        Pair("10d",R.drawable.img_10d),
        Pair("10n",R.drawable.img_10n),
        Pair("11d",R.drawable.img_11d),
        Pair("11n",R.drawable.img_11n),
        Pair("13d",R.drawable.img_13d),
        Pair("13n",R.drawable.img_13n),
        Pair("50d",R.drawable.img_50d),
        Pair("50n",R.drawable.img_50n),
    )


}