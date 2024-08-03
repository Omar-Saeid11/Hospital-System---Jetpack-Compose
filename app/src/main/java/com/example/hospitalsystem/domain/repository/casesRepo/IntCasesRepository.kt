package com.example.hospitalsystem.domain.repository.casesRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.cases.DomainModelCases
import com.example.hospitalsystem.domain.models.cases.showCase.DomainShowCaseResponse
import kotlinx.coroutines.flow.Flow

interface IntCasesRepository {
    suspend fun getAllCases(): Flow<Result<DomainModelCases>>
    suspend fun showCase(id: Int): Flow<Result<DomainShowCaseResponse>>
    suspend fun makeRequest(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ): Flow<Result<DomainCall>>

}