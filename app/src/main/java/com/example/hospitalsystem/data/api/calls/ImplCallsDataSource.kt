package com.example.hospitalsystem.data.api.calls

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.domain.entities.CallData
import javax.inject.Inject

class ImplCallsDataSource @Inject constructor(private val callsApiService: CallsApiService) :
    IntCallsDataSource {
    override suspend fun getAllCalls(date: String): Result<ModelAllCalls> {
        return try {
            val dataSource = callsApiService.getAllCalls(date)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun createCall(call: CallData): Result<Call> {
        return try {
            val dataSource = callsApiService.createCall(
                call.patientName,
                call.age.toString(), call.doctorId, call.phone, call.description
            )
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getDoctors(type: String, name: String): Result<ModelUserType> {
        return try {
            val dataSource = callsApiService.getDoctors(type, name)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}