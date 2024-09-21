package com.example.hospitalsystem.presentation.screens.attendance

import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.core.Utils.Companion.showMessage
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.screens.attendance.composables.FingerprintIcon
import com.example.hospitalsystem.presentation.screens.attendance.composables.InstructionText
import com.example.hospitalsystem.presentation.viewmodels.attendanceViewModel.AttendanceViewModel
import com.example.hospitalsystem.theme.Primary
import java.util.concurrent.Executor

@Composable
fun FingerprintScreen(
    navController: NavController,
    type: String,
    attendanceViewModel: AttendanceViewModel = hiltViewModel()
) {
    var verificationStatus by remember { mutableStateOf("") }
    val context = LocalContext.current
    val attendanceState by attendanceViewModel.attendanceState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            FingerprintIcon(onClick = {
                showBiometricPrompt(
                    activity = context as FragmentActivity,
                    onSuccess = {
                        verificationStatus = "Success: $type registered"
                        attendanceViewModel.attendance(type)
                        navController.navigate(Screen.RegisterSuccessScreen.route)
                    },
                    onFailure = { verificationStatus = "Failed: Try again" },
                    onError = { errorString -> verificationStatus = "Error: $errorString" }
                )
            })
            Spacer(modifier = Modifier.height(24.dp))
            InstructionText()
            Spacer(modifier = Modifier.height(16.dp))

            when (attendanceState) {
                is Result.Loading -> {
                    Text(text = "Loading...", color = Color.White, fontSize = 16.sp)
                }

                is Result.Success -> {
                    showMessage(context, ("Success"))
                }

                is Result.Error -> {
                    Text(
                        text = "Error: ${(attendanceState as Result.Error).exception.message}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }

            if (verificationStatus.isNotEmpty()) {
                Text(text = verificationStatus, color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


fun showBiometricPrompt(
    activity: FragmentActivity,
    onSuccess: () -> Unit,
    onFailure: () -> Unit,
    onError: (String) -> Unit
) {
    val executor: Executor = ContextCompat.getMainExecutor(activity)
    val biometricPrompt =
        BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                onFailure()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                onError(errString.toString())
            }
        })

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric Authentication")
        .setSubtitle("Verify your identity")
        .setNegativeButtonText("Cancel")
        .build()

    biometricPrompt.authenticate(promptInfo)
}
