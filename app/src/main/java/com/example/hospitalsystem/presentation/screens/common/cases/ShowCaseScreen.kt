package com.example.hospitalsystem.presentation.screens.common.cases


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.data.models.employee_model.EmployeeType
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.screens.common.cases.composables.BottomSheetContent
import com.example.hospitalsystem.presentation.screens.common.cases.composables.CaseInfo
import com.example.hospitalsystem.presentation.screens.common.cases.composables.MedicalMeasurementInfo
import com.example.hospitalsystem.presentation.screens.common.cases.composables.MedicalRecordInfo
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel
import com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel.CasesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CaseDetailsScreen(
    navController: NavController,
    caseId: Int,
    viewModel: CasesViewModel = hiltViewModel(),
    callsViewmodel: CallsViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Case", "Medical record", "Medical measurement")
    var selectedNurse by remember { mutableStateOf<PresentationUserType?>(null) }

    val selectedNurseState = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<PresentationUserType>("selectedNurse")

    selectedNurseState?.observe(LocalLifecycleOwner.current) { nurse ->
        selectedNurse = nurse
    }

    LaunchedEffect(caseId) {
        viewModel.showCase(caseId)
    }

    val caseState by viewModel.showCaseState.collectAsState()

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheetContent(
                caseId = caseId,
                nurseId = selectedNurse?.id ?: 0,
                type = EmployeeType.analysis.toString(),
                navToMedicalRecordScreen = { caseId, type ->
                    navController.navigate("${Screen.SelectionScreen.route}/$caseId/$type")
                },
                navToMedicalMeasurementScreen = { caseId, nurseId ->
                    navController.navigate("${Screen.MedicalMeasurementScreen.route}/$caseId/$nurseId")
                }
            )
        },
        sheetBackgroundColor = Color.Transparent
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Case Details",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    LazyRow(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(tabTitles.size) { index ->
                            val isSelected = selectedTabIndex == index
                            Chip(
                                onClick = { selectedTabIndex = index },
                                border = if (isSelected) BorderStroke(
                                    1.dp,
                                    Color.LightGray
                                ) else null,
                                colors = ChipDefaults.chipColors(
                                    backgroundColor = if (isSelected) Color(0xFF22C7B8) else Color(
                                        0xFFD3E6E9
                                    )
                                )
                            ) {
                                Text(
                                    text = tabTitles[index],
                                    color = if (isSelected) Color.White else Color.Black
                                )
                            }
                        }
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        val type = EmployeeType.nurse
                        when (selectedTabIndex) {
                            0 -> CaseInfo(
                                caseState = caseState,
                                selectedNurse = selectedNurse?.firstName ?: "N/A",
                                onClickAddNurse = {
                                    navController.navigate("${Screen.SelectionScreen.route}/$caseId/$type")
                                },
                                onClickAddRequest = {
                                    coroutineScope.launch {
                                        bottomSheetState.show()
                                    }
                                }
                            )

                            1 -> MedicalRecordInfo(caseState)
                            2 -> MedicalMeasurementInfo(caseState)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { callsViewmodel.logout(caseId) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(text = "End Case", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        )
    }
}