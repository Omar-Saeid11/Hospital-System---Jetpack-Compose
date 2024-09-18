package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.ReportResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
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
    ): Result<ModelCreateResponse> {
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

    override suspend fun answerReport(id: Int, answer: String): Result<ModelCreateResponse> {
        return try {
            val dataSource = reportApiService.answerReport(id, answer)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun endReport(id: Int): Result<ModelCreateResponse> {
        return try {
            val dataSource = reportApiService.endReport(id)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}