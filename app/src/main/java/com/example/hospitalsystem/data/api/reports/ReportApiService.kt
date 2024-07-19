package com.example.hospitalsystem.data.api.reports

import com.example.hospitalsystem.data.models.report.ReportResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateReport
import com.example.hospitalsystem.data.models.report.showReport.ModelShowReport
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReportApiService {
    @GET("reports")
    suspend fun getReports(): ReportResponse

    @GET("reports")
    suspend fun getReportsByDate(@Query("date") date: String): ReportResponse

    @FormUrlEncoded
    @POST("reports")
    suspend fun createReport(
        @Field("report_name") reportName: String,
        @Field("description") description: String
    ): ModelCreateReport

    @GET("reports/{id}")
    suspend fun showReport(@Path("id") id: Int): ModelShowReport
}