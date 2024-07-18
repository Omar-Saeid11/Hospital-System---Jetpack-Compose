package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.data.models.report.Report
import com.example.hospitalsystem.data.models.report.ReportResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReportApiService {
    @GET("reports")
    suspend fun getReports(): ReportResponse

    @GET("reports")
    suspend fun getReportsByDate(@Query("date") date: String): ReportResponse
}