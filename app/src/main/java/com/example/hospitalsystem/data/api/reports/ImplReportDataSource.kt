package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.ReportResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateReport
import com.example.hospitalsystem.data.models.report.showReport.ModelShowReport

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

    override suspend fun createReport(
        name: String,
        description: String
    ): Result<ModelCreateReport> {
        return try {
            val dataSource = reportApiService.createReport(name, description)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun showReportDetails(id: Int): Result<ModelShowReport> {
        return try {
            val dataSource = reportApiService.showReport(id)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}