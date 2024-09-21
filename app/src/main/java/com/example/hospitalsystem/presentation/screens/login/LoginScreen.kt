package com.example.hospitalsystem.presentation.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.AuthPref
import com.example.hospitalsystem.core.Constant
import com.example.hospitalsystem.core.Utils.Companion.showMessage
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.connectivity.ConnectivityStatus
import com.example.hospitalsystem.presentation.viewmodels.ConnectivityViewModel
import com.example.hospitalsystem.presentation.viewmodels.loginViewModel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    connectivityViewModel: ConnectivityViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isConnected by connectivityViewModel.isConnected.observeAsState(initial = true)
    val authPref = remember { AuthPref(context) }
    ConnectivityStatus(isConnected = isConnected)

    val loginUiState by loginViewModel.loginUiState.collectAsState()

    val signIn: (String, String, String) -> Unit = { email, password, token ->
        loginViewModel.login(
            email = email,
            password = password,
            deviceToken = token,
            onSuccess = { userType ->
                scope.launch {
                    authPref.setUserType(userType)
                    navToHome(userType, navController = navController)
                }
            },
            onError = { errorMessage ->
                showMessage(context, errorMessage)
            }
        )
    }

    LaunchedEffect(loginUiState) {
        if (loginUiState.isLoading)
            else if (loginUiState.data != null) {
        } else if (loginUiState.error != null) {
            showMessage(context, loginUiState.error!!)
        }
    }

    LoginContent(signIn = signIn, loading = loginUiState.isLoading)
}

fun navToHome(type: String, navController: NavController) {
    when (type) {
        Constant.HR -> navController.navigate(Screen.HrHomeScreen.route) {
            popUpTo(navController.graph.startDestinationRoute ?: "startDestination") {
                inclusive = true
            }
        }
        Constant.RECEPTIONIST -> navController.navigate(Screen.ReceptionistScreen.route) {
            popUpTo(navController.graph.startDestinationRoute ?: "startDestination") {
                inclusive = true
            }
        }
        Constant.DOCTOR -> navController.navigate(Screen.DoctorHomeScreen.route) {
            popUpTo(navController.graph.startDestinationRoute ?: "startDestination") {
                inclusive = true
            }
        }
        Constant.NURSE -> navController.navigate(Screen.NurseHomeScreen.route) {
            popUpTo(navController.graph.startDestinationRoute ?: "startDestination") {
                inclusive = true
            }
        }
        Constant.ANALYSIS -> navController.navigate(Screen.AnalysisScreen.route) {
            popUpTo(navController.graph.startDestinationRoute ?: "startDestination") {
                inclusive = true
            }
        }
        Constant.MANAGER -> navController.navigate(Screen.ManagerScreen.route) {
            popUpTo(navController.graph.startDestinationRoute ?: "startDestination") {
                inclusive = true
            }
        }
    }
}

