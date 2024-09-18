package com.example.hospitalsystem.presentation.screens.common.reports.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationReportData

@Composable
fun CommentsList(reportData: PresentationReportData) {
    Column {
        reportData.let { data ->
            CommentItem(
                name = data.user?.firstName ?: "",
                role = "Specialist - ${data.user?.specialist}",
                date = data.createdAt ?: "",
                comment = data.description ?: ""
            )

            if (data.manger?.firstName.isNullOrEmpty() && data.manger?.lastName.isNullOrEmpty()) {
                Text(
                    text = "Manager has not answered on this report until now",
                    modifier = Modifier.padding(8.dp),
                    color = Color.Gray
                )
            } else {
                CommentItem(
                    name = "${data.manger?.firstName} ${data.manger?.lastName}",
                    role = "Specialist - ${data.manger?.specialist}",
                    date = data.manger?.updatedAt ?: "",
                    comment = data.answer ?: ""
                )
            }
        }
    }
}