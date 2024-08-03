package com.example.hospitalsystem.data.api.calls

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.calls.ModelLogout
import com.example.hospitalsystem.data.models.calls.showCall.ModelCallDetails
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.domain.entities.CallData
import javax.inject.Inject

class ImplCallsDataSource @Inject constructor(private val callsApiService: CallsApiService) :
    IntCallsDataSource {
    override suspend fun getCalls(): Result<ModelAllCalls> {
        return try {
            val dataSource = callsApiService.getCalls()
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getCallsByDate(date: String): Result<ModelAllCalls> {
        return try {
            val dataSource = callsApiService.getCallsByDate(date)
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

    override suspend fun showCall(id: Int): Result<ModelCallDetails> {
        return try {
            val dataSource = callsApiService.getCallDetails(id)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun logout(id: Int): Result<ModelLogout> {
        return try {
            val dataSource = callsApiService.logout(id)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun acceptOrCancelCall(id: Int, statue: String): Result<Call> {
        return try {
            val dataSource = callsApiService.acceptOrCancelCall(id = id, status = statue)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun addNurse(callId: Int, nurseId: Int): Result<Call> {
        return try {
            val dataSource = callsApiService.addNurse(callId, nurseId)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


}