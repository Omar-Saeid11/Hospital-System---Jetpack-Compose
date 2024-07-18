package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.ReportResponse

class ImplReportDataSource constructor(private val reportApiService: ReportApiService) :
    IntReportDataSource {
    override suspend fun getReports(): Result<ReportResponse> {
        return try {
            val dataSource = reportApiService.getReports()
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getReportsByDate(date: String): Result<ReportResponse> {
        return try {
            val dataSource = reportApiService.getReportsByDate(date)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}