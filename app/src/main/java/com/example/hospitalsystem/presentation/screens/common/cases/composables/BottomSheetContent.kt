package com.example.hospitalsystem.presentation.screens.common.cases.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalsystem.theme.Primary

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
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary)
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
                color = if (isSelected) Primary else Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = if (isSelected) Primary else Color.Gray,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text,
            color = if (isSelected) Primary else Color.Gray
        )
    }
}
