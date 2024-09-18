package com.example.hospitalsystem.presentation.screens.nurse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.screens.nurse.composables.AddMeasurementButton
import com.example.hospitalsystem.presentation.screens.nurse.composables.AddMeasurementFields
import com.example.hospitalsystem.presentation.screens.nurse.composables.AddNoteField
import com.example.hospitalsystem.presentation.screens.nurse.composables.DoctorInfoSection
import com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel.CasesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMeasurementScreen(
    navController: NavController,
    viewModel: CasesViewModel = hiltViewModel(),
    caseId: Int
) {
    val showCaseState by viewModel.showCaseState.collectAsState()


    var bloodPressure by remember { mutableStateOf("120-129") }
    var sugarAnalysis by remember { mutableStateOf("Normal") }
    var temperature by remember { mutableStateOf("") }
    var fluidBalance by remember { mutableStateOf("") }
    var respiratoryRate by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    LaunchedEffect(caseId) {
        viewModel.showCase(caseId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add measurement") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                when (val result = showCaseState) {
                    is Result.Success -> {
                        val caseData = result.data
                        val doctorName = caseData.data?.doctorId ?: "Unknown Doctor"
                        val role = "Specialist: Doctor"
                        val measurementNote = caseData.data?.measurementNote ?: ""
                        DoctorInfoSection(
                            name = doctorName,
                            role = role,
                            note = measurementNote
                        )
                    }

                    is Result.Loading -> {
                        CircularProgressIndicator()
                    }

                    is Result.Error -> {
                        Text("Error loading case details")
                    }
                }
            }

            item {
                AddMeasurementFields(
                    bloodPressure = bloodPressure,
                    onBloodPressureChange = { bloodPressure = it },
                    sugarAnalysis = sugarAnalysis,
                    onSugarAnalysisChange = { sugarAnalysis = it },
                    temperature = temperature,
                    onTemperatureChange = { temperature = it },
                    fluidBalance = fluidBalance,
                    onFluidBalanceChange = { fluidBalance = it },
                    respiratoryRate = respiratoryRate,
                    onRespiratoryRateChange = { respiratoryRate = it },
                    heartRate = heartRate,
                    onHeartRateChange = { heartRate = it }
                )
            }

            item {
                AddNoteField(
                    note = note,
                    onNoteChange = { note = it }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    AddMeasurementButton {
                        viewModel.addMeasurement(
                            caseId = caseId,
                            bloodPressure = bloodPressure,
                            sugarAnalysis = sugarAnalysis,
                            tempreture = temperature,
                            fluidBalance = fluidBalance,
                            respiratoryRate = respiratoryRate,
                            heartRate = heartRate,
                            note = note,
                            status = "Pending"
                        )
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun outlinedTextFieldDefaults(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color(0xFF5FDCDC),
        unfocusedBorderColor = Color.Gray,
        focusedLabelColor = Color(0xFF5FDCDC),
        cursorColor = Color(0xFF5FDCDC)
    )
}
