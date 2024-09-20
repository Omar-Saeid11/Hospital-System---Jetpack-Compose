package com.example.hospitalsystem.presentation.screens.common.reports

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Utils.Companion.showMessage
import com.example.hospitalsystem.presentation.composables.TextInputCalls
import com.example.hospitalsystem.presentation.viewmodels.reports.ReportViewModel
import com.example.hospitalsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReportScreen(
    navController: NavController,
    reportViewModel: ReportViewModel = hiltViewModel()
) {
    var reportName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val uiState by reportViewModel.uiState.collectAsState()
    var reportNameError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var isCreatingReport by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text("Create Report") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInputCalls(
            hint = "Report Name",
            onValueChanged = { newValue ->
                reportName = newValue
                reportNameError = newValue.isEmpty()
            },
            isError = reportNameError,
            errorMessage = "Report name cannot be empty",
            keyboardType = KeyboardType.Text,
            lines = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextInputCalls(
            hint = "Description",
            onValueChanged = { newValue ->
                description = newValue
                descriptionError = newValue.isEmpty()
            },
            isError = descriptionError,
            errorMessage = "Description cannot be empty",
            keyboardType = KeyboardType.Text,
            lines = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CloudUpload,
                    contentDescription = "Upload Icon",
                    modifier = Modifier.size(64.dp),
                    tint = Primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier.padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Primary),
                    onClick = {
                        showMessage(context, "This feature will be available soon")
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Primary,
                        containerColor = Color.Transparent
                    )
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Upload Image")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Upload Image")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (!reportNameError && !descriptionError && !isCreatingReport) {
                    isCreatingReport = true
                    reportViewModel.createReport(reportName, description)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Primary),
            contentPadding = PaddingValues(12.dp)
        ) {
            if (isCreatingReport) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
            } else {
                Text("Create Report")
            }
        }

        when {
            uiState.isLoading -> {}

            uiState.error != null -> {
                Text("Error: ${uiState.error}", color = Color.Red)
            }

            uiState.createReportResponse != null -> {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {
                    Text("Report created successfully: ${uiState.createReportResponse!!.message}")
                }
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateReportScreen() {
    CreateReportScreen(navController = NavController(LocalContext.current))
}
