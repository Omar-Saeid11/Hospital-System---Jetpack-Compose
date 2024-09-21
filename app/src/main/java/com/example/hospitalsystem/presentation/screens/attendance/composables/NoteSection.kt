package com.example.hospitalsystem.presentation.screens.attendance.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteSection(note: String) {
    Card(
        backgroundColor = Color(0xFFFFE4E1),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp
    ) {
        Text(
            text = note,
            modifier = Modifier.padding(16.dp),
            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
        )
    }
}