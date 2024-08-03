package com.example.hospitalsystem.presentation.screens.common.cases


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalsystem.R
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.employee_model.EmployeeType
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationShowCaseResponse
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel.CasesViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun CaseDetailsScreen(
    navController: NavController,
    caseId: Int,
    viewModel: CasesViewModel = hiltViewModel()
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
                    navController.navigate("${Screen.NurseSelectionScreen.route}/$caseId/$type")
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
                                    navController.navigate("${Screen.NurseSelectionScreen.route}/$caseId/$type")
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
                        onClick = { /* handle end case */ },
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


@Composable
fun CaseInfo(
    caseState: Result<PresentationShowCaseResponse>,
    selectedNurse: String,
    onClickAddNurse: () -> Unit,
    onClickAddRequest: () -> Unit
) {
    when (caseState) {
        is Result.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                androidx.compose.material3.Text(
                    text = "Loading...",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        is Result.Success -> {
            val caseDetails = caseState.data.data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                caseDetails?.let {
                    InfoRow(label = "Patient Name", value = it.patientName ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Age", value = it.age ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Phone Number", value = it.phone ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Date", value = it.createdAt ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Doctor", value = it.doctorId ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    it.nurseId?.let { it1 ->
                        InfoRow(
                            label = "Nurse",
                            value = it1
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Status", value = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(it.caseStatus ?: "N/A", fontSize = 16.sp)
                            Image(
                                painter = painterResource(id = R.drawable.ic_delay),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(start = 4.dp)
                            )
                        }
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Case Description",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        it.description ?: "No description available",
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionButton(
                        text = "Add Nurse",
                        icon = Icons.Default.Add,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        onClickAddNurse()
                    }
                    ActionButton(
                        text = "Request",
                        icon = Icons.Default.Add,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        onClickAddRequest()
                    }
                }
            }
        }

        is Result.Error -> {
            Text(
                text = "Error loading case details",
                color = Color.Red,
            )
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(value, fontSize = 16.sp)
    }
}

@Composable
fun InfoRow(label: String, value: @Composable () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            label,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Gray
        )
        value()
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF22C7B8)),
        modifier = modifier
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.White)
    }
}

@Composable
fun MedicalRecordInfo(caseState: Result<PresentationShowCaseResponse>) {
    when (caseState) {
        is Result.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Success -> {
            val caseDetails = caseState.data.data
            caseDetails?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.ic_user),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(it.nurseId ?: "", fontWeight = FontWeight.Bold)
                            Text(
                                "Specialist - Nurse",
                                color = Color(0xFF22C7B8),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.description ?: "No description available",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Medical record", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Column {
                        MedicalMeasurementItem(
                            name = "Blood pressure",
                            value = it.bloodPressure ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Sugar analysis",
                            value = it.sugarAnalysis ?: "N/A"
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = rememberAsyncImagePainter(model = it.image),
                            contentDescription = ""
                        )
                    }
                }
            }
        }

        is Result.Error -> {
            Text(
                text = "Error loading case details",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun MedicalMeasurementInfo(caseState: Result<PresentationShowCaseResponse>) {
    when (caseState) {
        is Result.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Success -> {
            val caseDetails = caseState.data.data
            caseDetails?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.ic_user),
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(it.analysisId ?: "", fontWeight = FontWeight.Bold)
                            Text("Specialist - Analysis employee", color = Color(0xFF22C7B8))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.description ?: "No description available",
                        style = MaterialTheme.typography.body2
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Medical measurement", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))
                    Column {
                        MedicalMeasurementItem(
                            name = "Blood pressure",
                            value = it.bloodPressure ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Sugar analysis",
                            value = it.sugarAnalysis ?: "N/A"
                        )
                    }
                }
            }
        }

        is Result.Error -> {
            Text(
                text = "Error loading case details",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun MedicalMeasurementItem(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = null,
            tint = Color(0xFF22C7B8),
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(name)
        Spacer(modifier = Modifier.weight(1f))
        Text(value, color = Color.Gray)
    }
}

@Composable
fun BottomSheetContent(
    caseId: Int,
    nurseId: Int,
    type: String,
    navToMedicalRecordScreen: (caseId: Int, type: String) -> Unit,
    navToMedicalMeasurementScreen: (caseId: Int, nurseId: Int) -> Unit
) {
    var selectedOption by remember { mutableStateOf("Medical record") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            )
            .graphicsLayer {
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
                clip = true
            }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RequestOption(
                text = "Medical record",
                icon = Icons.Default.Description,
                onClick = {
                    selectedOption = "Medical record"
                },
                isSelected = selectedOption == "Medical record",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            RequestOption(
                text = "Medical measurement",
                icon = Icons.Default.Assessment,
                onClick = {
                    selectedOption = "Medical measurement"
                },
                isSelected = selectedOption == "Medical measurement",
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (selectedOption == "Medical record") {
                    navToMedicalRecordScreen(caseId, type)
                } else {
                    navToMedicalMeasurementScreen(caseId, nurseId)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF22C7B8))
        ) {
            Text(text = "Request", color = Color.White, fontSize = 16.sp)
        }
    }
}


@Composable
fun RequestOption(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = if (isSelected) Color(0xFF22C7B8) else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = if (isSelected) Color(0xFF22C7B8) else Color.Gray,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text,
            color = if (isSelected) Color(0xFF22C7B8) else Color.Gray
        )
    }
}
