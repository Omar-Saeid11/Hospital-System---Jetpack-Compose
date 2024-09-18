package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.screens.nurse.outlinedTextFieldDefaults

@Composable
fun AddNoteField(
    note: String,
    onNoteChange: (String) -> Unit
) {
    OutlinedTextField(
        value = note,
        onValueChange = { onNoteChange(it) },
        label = { Text("Add Note") },
        colors = outlinedTextFieldDefaults(),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    )
}
