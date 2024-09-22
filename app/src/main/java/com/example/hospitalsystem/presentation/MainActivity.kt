package com.example.hospitalsystem.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hospitalsystem.HospitalSysApp
import com.example.hospitalsystem.presentation.viewmodels.MainViewModel
import com.example.hospitalsystem.theme.HospitalSystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            HospitalSystemTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                val startDestination by mainViewModel.startDestination.collectAsState()

                HospitalSysApp(startDestination = startDestination)
            }
        }
    }
}
