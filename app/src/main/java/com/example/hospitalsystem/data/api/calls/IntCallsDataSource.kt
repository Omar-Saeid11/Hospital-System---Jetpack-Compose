package com.example.hospitalsystem.data.api.calls

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.calls.ModelLogout
import com.example.hospitalsystem.data.models.calls.showCall.ModelCallDetails
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.domain.entities.CallData

interface IntCallsDataSource {
    suspend fun getCalls(): Result<ModelAllCalls>
    suspend fun getCallsByDate(date: String): Result<ModelAllCalls>
    suspend fun createCall(call: CallData): Result<Call>
    suspend fun getDoctors(type: String, name: String): Result<ModelUserType>
    suspend fun showCall(id: Int): Result<ModelCallDetails>
    suspend fun logout(id: Int): Result<ModelLogout>
    suspend fun acceptOrCancelCall(id: Int, statue: String): Result<Call>
    suspend fun addNurse(callId: Int, nurseId: Int): Result<Call>
}