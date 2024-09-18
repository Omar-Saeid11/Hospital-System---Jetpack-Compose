package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.screens.nurse.outlinedTextFieldDefaults

@Composable
fun AddMeasurementFields(
    bloodPressure: String,
    onBloodPressureChange: (String) -> Unit,
    sugarAnalysis: String,
    onSugarAnalysisChange: (String) -> Unit,
    temperature: String,
    onTemperatureChange: (String) -> Unit,
    fluidBalance: String,
    onFluidBalanceChange: (String) -> Unit,
    respiratoryRate: String,
    onRespiratoryRateChange: (String) -> Unit,
    heartRate: String,
    onHeartRateChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        MeasurementRow(
            label = "Blood Pressure",
            selectedOption = bloodPressure,
            onOptionSelected = onBloodPressureChange
        )

        MeasurementRow(
            label = "Sugar Analysis",
            selectedOption = sugarAnalysis,
            onOptionSelected = onSugarAnalysisChange
        )

        MeasurementRow(
            label = "Temperature",
            selectedOption = temperature,
            onOptionSelected = onTemperatureChange
        )

        MeasurementRow(
            label = "Fluid Balance",
            selectedOption = fluidBalance,
            onOptionSelected = onFluidBalanceChange
        )

        MeasurementRow(
            label = "Respiratory Rate",
            selectedOption = respiratoryRate,
            onOptionSelected = onRespiratoryRateChange
        )

        MeasurementRow(
            label = "Heart Rate",
            selectedOption = heartRate,
            onOptionSelected = onHeartRateChange
        )
    }
}

@Composable
fun MeasurementRow(
    label: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(selectedOption) }
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { value ->
                textFieldValue = value
                onOptionSelected(value)
            },
            label = { Text(label) },
            modifier = Modifier.weight(1f),
            colors = outlinedTextFieldDefaults(),
            shape = RoundedCornerShape(8.dp)
        )
    }
}
