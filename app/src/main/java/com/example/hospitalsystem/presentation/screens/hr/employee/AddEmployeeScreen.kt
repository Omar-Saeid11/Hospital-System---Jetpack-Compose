package com.example.hospitalsystem.presentation.screens.hr.employee

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.screens.hr.composables.AddEmployeeContent
import com.example.hospitalsystem.presentation.viewmodels.hrViewModel.RegisterViewModel

@Composable
fun AddEmployeeScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var errorMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val registerState by registerViewModel.registerState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        AddEmployeeContent { registerData ->
            registerViewModel.register(registerData)
        }

        when (registerState) {
            is Result.Loading -> {}

            is Result.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Screen.EmployeeScreen.route) {
                        popUpTo(Screen.EmployeeScreen.route) {
                            inclusive = true
                        }
                    }

                }
            }

            is Result.Error -> {
                LaunchedEffect(Unit) {
                    errorMessage =
                        (registerState as Result.Error).exception.message ?: "Unknown error"
                }
            }
        }

        LaunchedEffect(errorMessage) {
            if (errorMessage.isNotEmpty()) {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = "Dismiss"
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) { data ->
            Snackbar(
                backgroundColor = Color.Red,
                contentColor = Color.White,
                action = {
                    TextButton(
                        onClick = {
                            data.performAction()
                            errorMessage = ""
                        },
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
                    ) {
                        Text(data.actionLabel ?: "Dismiss")
                    }
                }
            ) {
                Text(data.message)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddEmployeeScreen() {
    AddEmployeeContent {

    }
}