package com.example.hospitalsystem.presentation.screens.nurse.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFF5FDCDC) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                BorderStroke(
                    1.dp,
                    if (isSelected) Color(0xFF5FDCDC) else Color.Gray
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .wrapContentWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            maxLines = 1
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.Red, shape = CircleShape)
            )
        }
    }
}