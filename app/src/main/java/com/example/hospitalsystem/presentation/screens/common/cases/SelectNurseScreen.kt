package com.example.hospitalsystem.presentation.screens.common.cases

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalsystem.R
import com.example.hospitalsystem.application.navigation.Screen
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationModelUserType
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NurseSelectionScreen(
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
                    if (type == "analysis") {
                        Text("Select Analysis")
                    } else {
                        Text("Select Nurse")
                    }
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
                    SearchBarSelectNurse(searchText) { searchText = it }

                    when (nursesState) {
                        is Result.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        is Result.Success -> {
                            val nurses =
                                (nursesState as Result.Success<PresentationModelUserType>).data.data
                            NurseList(
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
                                if (type == "analysis") {
                                    navController.navigate("${Screen.MedicalMeasurementScreen.route}/${caseId}/${nurse.id}")
                                } else {
                                    viewModel.addNurse(caseId, nurse.id)
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        "selectedNurse",
                                        selectedNurse
                                    )
                                    navController.popBackStack()
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color.Transparent),
                        colors = ButtonDefaults.buttonColors(Color(0xFF22C7B8))
                    ) {
                        if (type == "analysis") {
                            Text("Select Analysis")
                        } else {
                            Text("Select Nurse")
                        }

                    }
                }
            }
        }
    )
}


@Composable
fun NurseList(
    nurses: List<PresentationUserType>,
    selectedNurse: PresentationUserType?,
    onNurseSelected: (PresentationUserType) -> Unit
) {
    LazyColumn {
        items(nurses) { nurse ->
            NurseListItem(
                nurse = nurse,
                isSelected = nurse == selectedNurse,
                onNurseSelected = onNurseSelected
            )
            Divider()
        }
    }
}

@Composable
fun NurseListItem(
    nurse: PresentationUserType,
    isSelected: Boolean,
    onNurseSelected: (PresentationUserType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNurseSelected(nurse) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(60.dp)) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.ic_user),
                contentDescription = "Nurse Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
                    .align(Alignment.BottomEnd)
                    .offset(4.dp, 4.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(nurse.firstName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(nurse.type, color = Color.Gray, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        RadioButton(
            selected = isSelected,
            onClick = { onNurseSelected(nurse) }
        )
    }
}

@Composable
fun SearchBarSelectNurse(searchText: String, onSearchTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Search Nurse") },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    NurseSelectionScreen(navController = NavController(LocalContext.current), caseId = 1, type = "")
}
