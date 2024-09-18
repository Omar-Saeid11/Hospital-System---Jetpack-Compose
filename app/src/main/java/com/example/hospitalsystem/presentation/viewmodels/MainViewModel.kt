package com.example.hospitalsystem.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.core.AuthPref
import com.example.hospitalsystem.core.Constant
import com.example.hospitalsystem.core.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authPref: AuthPref
) : ViewModel() {

    private val _startDestination = MutableStateFlow(Screen.SplashScreen.route)
    val startDestination: StateFlow<String> = _startDestination

    init {
        viewModelScope.launch {
            val userType = authPref.userType.first()
            _startDestination.value = when (userType) {
                Constant.RECEPTIONIST -> Screen.ReceptionistScreen.route
                Constant.HR -> Screen.HrHomeScreen.route
                Constant.DOCTOR -> Screen.DoctorHomeScreen.route
                Constant.NURSE -> Screen.NurseHomeScreen.route
                Constant.ANALYSIS -> Screen.AnalysisScreen.route
                Constant.MANAGER -> Screen.ManagerScreen.route
                else -> Screen.SplashScreen.route
            }
        }
    }
}
