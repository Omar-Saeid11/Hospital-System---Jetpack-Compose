package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hospitalsystem.core.UserPreferences
import com.example.hospitalsystem.navigation.Screen
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationDataShowCase

@Composable
fun CaseTypeSection(
    caseDetails: PresentationDataShowCase,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    navController: NavController
) {
    val userType = UserPreferences.getUserType()
    val medicalTabText = if (userType == "analysis") "Medical Record" else "Medical Measurement"

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabButton(
                text = "Case Status",
                isSelected = selectedTab == "Case Status",
                onClick = { onTabSelected("Case Status") }
            )
            TabButton(
                text = medicalTabText,
                isSelected = selectedTab == medicalTabText,
                onClick = { onTabSelected(medicalTabText) }
            )
        }

        when (selectedTab) {
            "Case Status" -> CaseDetailsSection(caseDetails)
            medicalTabText -> {
                if (userType == "nurse") {
                    navController.navigate("${Screen.AddMeasurementScreen.route}/${caseDetails.id}")
                } else {
                    MedicalRecordSection(caseDetails)
                }
            }
        }
    }
}
