package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.ReportResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateReport
import com.example.hospitalsystem.data.models.report.showReport.ModelShowReport

interface IntReportDataSource {
    suspend fun getReports(): Result<ReportResponse>
    suspend fun getReportsByDate(date: String): Result<ReportResponse>
    suspend fun createReport(name: String, description: String): Result<ModelCreateReport>
    suspend fun showReportDetails(id: Int): Result<ModelShowReport>
}