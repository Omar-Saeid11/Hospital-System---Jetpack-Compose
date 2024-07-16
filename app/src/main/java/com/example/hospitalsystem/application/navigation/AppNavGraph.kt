package com.example.hospitalsystem.application.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hospitalsystem.presentation.screens.SplashScreen
import com.example.hospitalsystem.presentation.screens.hr.AddEmployeeScreen
import com.example.hospitalsystem.presentation.screens.hr.HrHomeScreen
import com.example.hospitalsystem.presentation.screens.hr.employee.EmployeeScreen
import com.example.hospitalsystem.presentation.screens.login.LoginScreen
import com.example.hospitalsystem.presentation.screens.profile.ProfileScreen
import com.example.hospitalsystem.presentation.screens.receptionist.ReceptionistScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.CallsScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.CreateCallScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.DoctorSelectionScreen
import com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call.RequestCanceledScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.SplashScreen.route) { SplashScreen(navController) }
        composable(Screen.LoginScreen.route) { LoginScreen(navController) }
        composable(Screen.ReceptionistScreen.route) { ReceptionistScreen(navController) }
        composable(Screen.CallsScreen.route) { CallsScreen(navController) }
        composable(Screen.CreateCallScreen.route) {
            CreateCallScreen(navController = navController)
        }
        composable(Screen.RequestCanceledScreen.route) {
            RequestCanceledScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) { ProfileScreen() }
        composable(Screen.HrHomeScreen.route) { HrHomeScreen(navController = navController) }
        composable(Screen.EmployeeScreen.route) { EmployeeScreen(navController = navController) }
        composable(Screen.AddEmployeeScreen.route) {
            AddEmployeeScreen(navController = navController)
        }
        composable(Screen.DoctorSelectionScreen.route) {
            DoctorSelectionScreen(navController = navController)
        }
    }
}
