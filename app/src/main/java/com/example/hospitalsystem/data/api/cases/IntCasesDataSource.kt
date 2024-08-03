package com.example.hospitalsystem.data.api.cases

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.cases.ModelCasesResponse
import com.example.hospitalsystem.data.models.cases.showCase.ShowCaseResponse
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType

interface IntCasesDataSource {
    suspend fun getAllCases(): Result<ModelCasesResponse>
    suspend fun showCase(id: Int): Result<ShowCaseResponse>
    suspend fun makeRequest(callId: Int, userId: Int, note: String, types: List<String>):Result<Call>
}