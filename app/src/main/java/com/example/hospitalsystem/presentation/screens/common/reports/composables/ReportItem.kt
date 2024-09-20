package com.example.hospitalsystem.presentation.screens.common.reports.composables

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
import com.example.hospitalsystem.presentation.models.report.PresentationReport
import com.example.hospitalsystem.theme.Primary

@Composable
fun ReportItem(report: PresentationReport, onReportClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onReportClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White)
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
                                Primary,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        tint = Color.White
                    )
                    Text(
                        text = report.reportName ?: "Report Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                }
                Text(
                    text = if (report.status == "pending") "Process" else "Finished",
                    color = if (report.status == "pending") Color(0xFFFFA000) else Color(0xFF00E676),
                    modifier = Modifier
                        .background(
                            color = if (report.status == "pending") Color(0xFFFFECB3) else Color(
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
                            Primary,
                            shape = RoundedCornerShape(4.dp)
                        ),
                    tint = Color.White
                )
                Text(
                    text = report.createdAt ?: "Date",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}
