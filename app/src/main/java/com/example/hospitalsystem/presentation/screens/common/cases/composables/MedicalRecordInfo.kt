package com.example.hospitalsystem.presentation.screens.common.cases.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationShowCaseResponse
import com.example.hospitalsystem.theme.Primary

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
                                color = Primary,
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
                            name = "Blood Pressure",
                            value = it.bloodPressure ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Sugar Analysis",
                            value = it.sugarAnalysis ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Heart Rate",
                            value = it.heartRate ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Temperature",
                            value = it.tempreture ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Fluid Balance",
                            value = it.fluidBalance ?: "N/A"
                        )
                        MedicalMeasurementItem(
                            name = "Respiratory Rate",
                            value = it.respiratoryRate ?: "N/A"
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