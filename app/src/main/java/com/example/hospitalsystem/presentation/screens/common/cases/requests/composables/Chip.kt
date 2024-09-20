package com.example.hospitalsystem.presentation.screens.common.cases.requests.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.theme.Primary


@Composable
fun Chip(
    text: String,
    onClose: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Primary,
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = text, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.White)
            }
        }
    }
}
