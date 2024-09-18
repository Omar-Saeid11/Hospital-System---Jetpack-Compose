package com.example.hospitalsystem.data.api.cases

import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.cases.ModelCasesResponse
import com.example.hospitalsystem.data.models.cases.showCase.ShowCaseResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CasesApiService {
    @GET("case")
    suspend fun getAllCases(): ModelCasesResponse

    @GET("case/{id}")
    suspend fun showCase(@Path("id") id: Int): ShowCaseResponse

    @FormUrlEncoded
    @POST("make-request")
    suspend fun makeRequest(
        @Field("call_id") callId: Int,
        @Field("user_id") userId: Int,
        @Field("note") note: String,
        @Field("types[]") types: List<String>
    ): Call

    @FormUrlEncoded
    @POST("measurement")
    suspend fun addMeasurement(
        @Field("call_id") caseId: Int,
        @Field("blood_pressure") bloodPressure: String,
        @Field("sugar_analysis") sugarAnalysis: String,
        @Field("tempreture") tempreture: String,
        @Field("fluid_balance") fluidBalance: String,
        @Field("respiratory_rate") respiratoryRate: String,
        @Field("heart_rate") heartRate: String,
        @Field("note") not: String,
        @Field("status") status: String
    ): ModelCreateResponse
}