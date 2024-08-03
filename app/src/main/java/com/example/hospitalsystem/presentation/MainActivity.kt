package com.example.hospitalsystem.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hospitalsystem.application.HospitalSysApp
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.application.theme.HospitalSystemTheme
import com.example.hospitalsystem.core.AuthPref
import com.example.hospitalsystem.core.Constant
import com.example.hospitalsystem.core.UserPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userPreferences: AuthPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val startDestination = runBlocking {
            if (userPreferences.isLoggedIn.first()) {
                when (UserPreferences.getUserType()) {
                    Constant.RECEPTIONIST -> Screen.ReceptionistScreen.route
                    Constant.HR -> Screen.HrHomeScreen.route
                    Constant.DOCTOR->Screen.DoctorHomeScreen.route
                    else -> Screen.SplashScreen.route
                }
            } else {
                Screen.SplashScreen.route
            }
        }

        setContent {
            HospitalSystemTheme {
                HospitalSysApp(startDestination = startDestination)
            }
        }
    }
}

