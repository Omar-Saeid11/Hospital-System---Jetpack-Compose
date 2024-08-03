package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.data.models.employee_model.EmployeeType

@ExperimentalMaterialApi
@Composable
fun TypeFilter(selectedType: EmployeeType, onTypeSelected: (EmployeeType) -> Unit) {
    val types = EmployeeType.entries.toList()
    LazyRow {
        items(types) { type ->
            Chip(
                onClick = { onTypeSelected(type) },
                modifier = Modifier.padding(4.dp),
                border = if (selectedType == type) BorderStroke(1.dp, Color.LightGray) else null,
                colors = ChipDefaults.chipColors(
                    backgroundColor = if (selectedType == type) Color(0xFF22C7B8) else Color(
                        0xFFD3E6E9
                    )
                )
            ) {
                Text(text = type.name)
            }
        }
    }
}
