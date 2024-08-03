package com.example.hospitalsystem.presentation.screens.receptionist.calls.create_call

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
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.composables.SearchBarSelectDoctor
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationModelUserType
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType
import com.example.hospitalsystem.presentation.viewmodels.callsViewModel.CallsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorSelectionScreen(
    navController: NavController,
    viewModel: CallsViewModel = hiltViewModel()
) {
    val doctorsState by viewModel.doctors.collectAsState()
    var selectedDoctor by rememberSaveable { mutableStateOf<PresentationUserType?>(null) }
    var searchText by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getDoctors("doctor", "")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Doctor") },
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
                    SearchBarSelectDoctor(searchText) { searchText = it }

                    when (doctorsState) {
                        is Result.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }

                        is Result.Success -> {
                            val doctors =
                                (doctorsState as Result.Success<PresentationModelUserType>).data.data
                            DoctorList(
                                doctors = doctors.filter {
                                    it.firstName.contains(searchText, ignoreCase = true)
                                },
                                selectedDoctor = selectedDoctor,
                                onDoctorSelected = { doctor -> selectedDoctor = doctor }
                            )
                        }

                        is Result.Error -> {
                            Text(
                                "Failed to load doctors",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }

                if (selectedDoctor != null) {
                    Button(
                        onClick = {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                "selectedDoctor",
                                selectedDoctor
                            )
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color.Transparent),
                        colors = ButtonDefaults.buttonColors(Color(0xFF22C7B8))
                    ) {
                        Text("Select Doctor")
                    }
                }
            }
        }
    )
}

@Composable
fun DoctorList(
    doctors: List<PresentationUserType>,
    selectedDoctor: PresentationUserType?,
    onDoctorSelected: (PresentationUserType) -> Unit
) {
    LazyColumn {
        items(doctors) { doctor ->
            DoctorListItem(
                doctor = doctor,
                isSelected = doctor == selectedDoctor,
                onDoctorSelected = onDoctorSelected
            )
            Divider()
        }
    }
}


@Composable
fun DoctorListItem(
    doctor: PresentationUserType,
    isSelected: Boolean,
    onDoctorSelected: (PresentationUserType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDoctorSelected(doctor) }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(60.dp)) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.ic_user),
                contentDescription = "Doctor Image",
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
            Text(doctor.firstName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(doctor.type, color = Color.Gray, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        RadioButton(
            selected = isSelected,
            onClick = { onDoctorSelected(doctor) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    DoctorSelectionScreen(navController = NavController(LocalContext.current))
}
