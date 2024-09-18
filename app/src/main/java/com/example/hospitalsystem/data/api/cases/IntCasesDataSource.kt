package com.example.hospitalsystem.data.api.cases

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.cases.ModelCasesResponse
import com.example.hospitalsystem.data.models.cases.showCase.ShowCaseResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse

interface IntCasesDataSource {
    suspend fun getAllCases(): Result<ModelCasesResponse>
    suspend fun showCase(id: Int): Result<ShowCaseResponse>
    suspend fun makeRequest(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ): Result<Call>

    suspend fun addMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String
    ):Result<ModelCreateResponse>
}