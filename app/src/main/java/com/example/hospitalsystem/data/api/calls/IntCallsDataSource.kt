package com.example.hospitalsystem.data.api.calls

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.domain.entities.CallData

interface IntCallsDataSource {
    suspend fun getAllCalls(date: String): Result<ModelAllCalls>
    suspend fun createCall(call: CallData): Result<Call>
    suspend fun getDoctors(type: String, name: String): Result<ModelUserType>
}