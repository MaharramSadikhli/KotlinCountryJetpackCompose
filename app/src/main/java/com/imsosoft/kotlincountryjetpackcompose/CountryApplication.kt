package com.imsosoft.kotlincountryjetpackcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CountryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}