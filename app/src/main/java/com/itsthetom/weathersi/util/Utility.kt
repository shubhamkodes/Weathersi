package com.itsthetom.weathersi.util

import android.graphics.Color
import android.view.Window
import androidx.core.view.WindowCompat

object Utility {

    fun hideSystemUI(window: Window) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor= Color.TRANSPARENT
    }
}