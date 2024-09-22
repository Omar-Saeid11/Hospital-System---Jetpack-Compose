package com.example.hospitalsystem.presentation.screens.common.cases

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationModelUserType
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.screens.common.cases.composables.EmployeeSelectionList
import com.example.hospitalsystem.presentation.screens.common.cases.composables.SearchBarSelectEmployee
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel
import com.example.hospitalsystem.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    navController: NavController,
    viewModel: CallsViewModel = hiltViewModel(),
    caseId: Int,
    type: String
) {
    val nursesState by viewModel.doctors.collectAsState()
    var selectedNurse by rememberSaveable { mutableStateOf<PresentationUserType?>(null) }
    var searchText by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getDoctors(type, "")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (type) {
                            "analysis" -> "Select Analysis"
                            "nurse" -> "Select Nurse"
                            else -> "Select User"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 64.dp)
                        .background(Color.Transparent)
                ) {
                    SearchBarSelectEmployee(searchText) { searchText = it }

                    when (nursesState) {
                        is Result.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        is Result.Success -> {
                            val nurses =
                                (nursesState as Result.Success<PresentationModelUserType>).data.data
                            EmployeeSelectionList(
                                nurses = nurses.filter {
                                    it.firstName.contains(searchText, ignoreCase = true)
                                },
                                selectedNurse = selectedNurse,
                                onNurseSelected = { nurse -> selectedNurse = nurse }
                            )
                        }

                        is Result.Error -> {
                            Text(
                                "Failed to load nurses",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                if (selectedNurse != null) {
                    Button(
                        onClick = {
                            selectedNurse?.let { nurse ->
                                if (type == "analysis" && UserPreferences.getUserType() != "manger") {
                                    navController.navigate("${Screen.MedicalMeasurementScreen.route}/${caseId}/${nurse.id}")
                                } else {
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        "selectedNurse",
                                        nurse
                                    )
                                    viewModel.addUser(caseId, nurse.id)
                                    navController.popBackStack()
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(Primary)
                    ) {
                        Text(
                            when (type) {
                                "analysis" -> "Select Analysis"
                                else -> "Select Nurse"
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    SelectionScreen(navController = NavController(LocalContext.current), caseId = 1, type = "")
}
