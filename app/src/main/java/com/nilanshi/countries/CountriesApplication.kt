package com.nilanshi.countries

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CountriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
