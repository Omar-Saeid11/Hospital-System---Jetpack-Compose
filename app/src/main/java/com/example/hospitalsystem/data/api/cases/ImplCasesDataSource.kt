package com.example.hospitalsystem.data.api.cases

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.cases.ModelCasesResponse
import com.example.hospitalsystem.data.models.cases.showCase.ShowCaseResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import javax.inject.Inject

class ImplCasesDataSource @Inject constructor(private val casesApiService: CasesApiService) :
    IntCasesDataSource {
    override suspend fun getAllCases(): Result<ModelCasesResponse> {
        return try {
            val dataSource = casesApiService.getAllCases()
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun showCase(id: Int): Result<ShowCaseResponse> {
        return try {
            val dataSource = casesApiService.showCase(id)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun makeRequest(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ): Result<Call> {
        return try {
            val dataSource = casesApiService.makeRequest(callId, userId, note, types)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun addMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String
    ): Result<ModelCreateResponse> {
        return try {
            val dataSource = casesApiService.addMeasurement(
                caseId,
                bloodPressure,
                sugarAnalysis,
                tempreture,
                fluidBalance,
                respiratoryRate,
                heartRate,
                not,
                status
            )
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}