package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationDataShowCase
import com.example.hospitalsystem.theme.Primary

@Composable
fun MedicalRecordSection(caseDetails: PresentationDataShowCase) {
    Scaffold(
        bottomBar = {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary)
            ) {
                Text(text = "Add Record", color = Color.White)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DoctorProfileSection(caseDetails)
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                MedicalRecordCategories()
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                UploadImageSection()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
