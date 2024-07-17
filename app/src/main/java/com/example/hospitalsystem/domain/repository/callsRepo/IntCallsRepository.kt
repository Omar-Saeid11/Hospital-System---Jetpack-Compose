package com.example.hospitalsystem.domain.repository.callsRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.CallData
import com.example.hospitalsystem.domain.models.calls.DomainAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.calls.DomainLogout
import com.example.hospitalsystem.domain.models.calls.showCall.DomainCallDetails
import com.example.hospitalsystem.domain.models.hr.userType.DomainModelUserType
import kotlinx.coroutines.flow.Flow

interface IntCallsRepository {
    suspend fun getAllCalls(date: String): Flow<Result<DomainAllCalls>>
    suspend fun createCall(call: CallData): Flow<Result<DomainCall>>
    suspend fun getDoctors(type: String, name: String): Flow<Result<DomainModelUserType>>
    suspend fun showCall(id: Int): Flow<Result<DomainCallDetails>>
    suspend fun logout(id: Int): Flow<Result<DomainLogout>>
}