package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationDataShowCase
import com.example.hospitalsystem.theme.Primary

@Composable
fun CaseDetailsSection(caseDetails: PresentationDataShowCase) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 72.dp)
        ) {
            RequestCard(caseDetails)
            Spacer(modifier = Modifier.height(16.dp))

            CaseDetailItem(label = "Patient Name", value = caseDetails.patientName ?: "N/A")
            CaseDetailItem(label = "Age", value = caseDetails.age ?: "N/A")
            CaseDetailItem(label = "Phone Number", value = caseDetails.phone ?: "N/A")
            CaseDetailItem(label = "Date", value = caseDetails.createdAt ?: "N/A")
            CaseDetailItem(label = "Doctor", value = caseDetails.doctorId ?: "N/A")
            CaseDetailItem(label = "Nurse", value = caseDetails.nurseId ?: "N/A")
            CaseDetailItem(
                label = "Status",
                value = caseDetails.caseStatus ?: "N/A",
                isStatus = true
            )
            CaseDetailItem(
                label = "Case Description",
                value = caseDetails.description ?: "N/A",
                isDescription = true
            )
        }

        if (UserPreferences.getUserType() != "analysis") {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Call Doctor")
            }
        }
    }
}
