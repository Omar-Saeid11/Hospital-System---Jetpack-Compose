package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.theme.Primary

@Composable
fun MedicalRecordCategories() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MedicalCategoryButton(text = "SGOT, SGPT")
        MedicalCategoryButton(text = "ESR")
        MedicalCategoryButton(text = "Lipid Profile")
    }
}

@Composable
fun MedicalCategoryButton(text: String) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = Primary),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.wrapContentSize()
    ) {
        Text(text = text, color = Color.White)
    }
}