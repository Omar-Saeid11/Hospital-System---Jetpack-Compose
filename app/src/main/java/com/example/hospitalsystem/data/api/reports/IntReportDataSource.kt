package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.report.Report
import com.example.hospitalsystem.data.models.report.ReportResponse

interface IntReportDataSource {
    suspend fun getReports():Result<ReportResponse>
    suspend fun getReportsByDate(date: String): Result<ReportResponse>
}