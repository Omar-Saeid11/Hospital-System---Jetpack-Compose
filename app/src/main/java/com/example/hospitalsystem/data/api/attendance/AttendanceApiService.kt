package com.example.hospitalsystem.data.api.attendance

import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AttendanceApiService {
    @FormUrlEncoded
    @POST("attendance")
    suspend fun attendance(@Field("status") status: String): ModelCreateResponse
}