package com.example.hospitalsystem.presentation.screens.common.cases.composables

import androidx.compose.foundation.Image
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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalsystem.R
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationShowCaseResponse

@Composable
fun CaseInfo(
    caseState: Result<PresentationShowCaseResponse>,
    selectedNurse: String?,
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
                    InfoRow(label = "Age", value = it.age?.toString() ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Phone Number", value = it.phone ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    InfoRow(label = "Date", value = it.createdAt ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow(label = "Doctor", value = it.doctorId ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow(label = "Nurse",
                        value = selectedNurse ?: caseDetails.nurseId ?: caseDetails.analysisId
                        ?: "N/A"
                    )
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
                        modifier = Modifier.weight(1f)
                    ) {
                        onClickAddNurse()
                    }
                    ActionButton(
                        text = "Request",
                        icon = Icons.Default.Add,
                        modifier = Modifier.weight(1f)
                    ) {
                        onClickAddRequest()
                    }
                }
            }
        }

        is Result.Error -> {
            Text(
                text = "Error loading case details: ${caseState.exception.message}",
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
