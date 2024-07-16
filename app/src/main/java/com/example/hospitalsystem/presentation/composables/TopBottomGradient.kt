package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

val gradientBrush = Brush.linearGradient(
    colors = listOf(Color(0x669BF3EB), Color.Transparent),
    start = Offset(134f, 0f),
    end = Offset(134f, 750f)
)

val gradientBrushBottom = Brush.linearGradient(
    colors = listOf(Color.Transparent, Color(0x669BF3EB)),
    start = Offset(134f, 0f),
    end = Offset(134f, 750f)
)

@Composable
fun TopBottomGradient() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(screenWidth * 0.7f, screenHeight * 0.3f)
                .clip(RoundedCornerShape(topEnd = 100.dp))
                .background(brush = gradientBrush)
        )
        Box(
            modifier = Modifier
                .size(screenWidth * 0.7f, screenHeight * 0.3f)
                .clip(RoundedCornerShape(bottomStart = 100.dp))
                .background(brush = gradientBrushBottom)
                .align(Alignment.BottomEnd)
        )
    }
}