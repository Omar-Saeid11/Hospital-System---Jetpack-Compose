package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.theme.Primary

@Composable
fun CustomDropdownMenu(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    leadingIcon: ImageVector?
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(4.dp)) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                if (leadingIcon != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = Primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        VerticalDivider(
                            color = Primary,
                            modifier = Modifier
                                .height(24.dp)
                                .width(1.dp)
                        )
                    }
                }
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Select Option",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Primary
            ),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Select Option") },
            shape = RoundedCornerShape(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                ) {
                    Text(option)
                }
            }
        }
    }
}
