package com.example.hospitalsystem.presentation.screens.attendance.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@Composable
fun FingerprintIcon(onClick: () -> Unit) {
    Icon(
        painter = rememberVectorPainter(Icons.Outlined.Fingerprint),
        contentDescription = "Fingerprint",
        tint = Color.White,
        modifier = Modifier
            .size(150.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
    )
}
