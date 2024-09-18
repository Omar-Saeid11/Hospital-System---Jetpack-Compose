package com.example.hospitalsystem.presentation.screens.common.reports.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.models.report.PresentationReport

@Composable
fun ReportsList(calls: List<PresentationReport?>, onReportClick: (Int) -> Unit) {
    LazyColumn {
        items(calls) { call ->
            call?.let {
                ReportItem(it) { onReportClick(it.id ?: 0) }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
