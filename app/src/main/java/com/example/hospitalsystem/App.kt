package com.example.hospitalsystem

import android.app.Application
import com.example.hospitalsystem.core.UserPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        UserPreferences.init(this)
    }
}
