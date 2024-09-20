package com.example.hospitalsystem.presentation.screens.common.cases.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.screens.common.cases.requests.composables.Chip
import com.example.hospitalsystem.presentation.viewmodels.casesViewMOdel.CasesViewModel
import com.example.hospitalsystem.theme.Primary

@Composable
fun MedicalMeasurementScreen(
    navController: NavController,
    caseId: Int,
    nurseId: Int,
    casesViewModel: CasesViewModel = hiltViewModel()
) {
    var noteText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedMeasurements by remember { mutableStateOf(listOf<String>()) }

    var showDialog by remember { mutableStateOf(false) }
    var tempSelectedMeasurement by remember { mutableStateOf<String?>(null) }
    val availableMeasurements = listOf(
        "Blood Pressure",
        "Sugar measurement",
        "Temperature",
        "Fluid balance",
        "Respiratory rate",
        "Heart rate"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 24.dp),
                title = { Text(text = "Medical measurement") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                elevation = 0.dp
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                items(selectedMeasurements) { measurement ->
                    Chip(
                        text = measurement,
                        onClose = {
                            selectedMeasurements = selectedMeasurements.filter { it != measurement }
                        }
                    )
                }
                item {
                    OutlinedButton(onClick = { showDialog = true }) {
                        Text(text = "Add measurement")
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = noteText,
                onValueChange = { noteText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray),
                placeholder = { Text(text = "Add Note") }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    casesViewModel.makeRequest(
                        callId = caseId,
                        userId = nurseId,
                        note = noteText.text,
                        types = selectedMeasurements
                    )
                    navController.popBackStack("${Screen.ShowCaseScreen.route}/${caseId}", false)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Primary)
            ) {
                Text(text = "Send", color = Color.White)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Select Measurement") },
            text = {
                Column {
                    availableMeasurements.forEach { measurement ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    tempSelectedMeasurement = measurement
                                }
                                .padding(vertical = 4.dp)
                                .background(
                                    if (tempSelectedMeasurement == measurement)
                                        Primary
                                    else
                                        Color.Transparent
                                )
                        ) {
                            Text(
                                text = measurement,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth(),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        tempSelectedMeasurement?.let {
                            if (!selectedMeasurements.contains(it)) {
                                selectedMeasurements = selectedMeasurements + it
                            }
                        }
                        tempSelectedMeasurement = null
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMedicalMeasurementScreen() {
    MedicalMeasurementScreen(NavController(LocalContext.current), 0, 0)
}
