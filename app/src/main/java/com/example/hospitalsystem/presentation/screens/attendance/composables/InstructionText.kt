package com.example.hospitalsystem.presentation.screens.attendance.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun InstructionText() {
    Text(
        text = "Please touch ID sensor to verify registration",
        color = Color.White,
        fontSize = 16.sp,
    )
}
