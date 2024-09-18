package com.example.hospitalsystem.presentation.screens.common.tasks.Composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalsystem.presentation.models.tasks.PresentationData

@Composable
fun TaskItem(task: PresentationData, onTaskClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTaskClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFFFFF))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Description,
                        contentDescription = "Report",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 2.dp)
                            .background(
                                Color(0xFF22C7B8),
                                shape = RoundedCornerShape(4.dp)
                            ),
                        tint = Color.White
                    )
                    Text(
                        text = task.taskName ?: "Report Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
                Text(
                    text = if (task.status == "pending") "Process" else "Finished",
                    color = if (task.status == "pending") Color(0xFFFFA000) else Color(0xFF00E676),
                    modifier = Modifier
                        .background(
                            color = if (task.status == "pending") Color(0xFFFFECB3) else Color(
                                0xFFB9F6CA
                            ),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Date",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 2.dp)
                        .background(
                            Color(0xFF22C7B8),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    tint = Color.White
                )
                Text(
                    text = task.createdAt ?: "Date",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}
