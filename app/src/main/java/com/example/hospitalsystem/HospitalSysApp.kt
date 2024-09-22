package com.example.hospitalsystem

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.hospitalsystem.navigation.AppNavGraph

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HospitalSysApp(startDestination: String) {
    val navController = rememberNavController()
    Scaffold {
        AppNavGraph(navController = navController, startDestination = startDestination)
    }
}