package com.example.hospitalsystem.data.api.hr

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.hr.register.ModelRegister
import com.example.hospitalsystem.domain.entities.RegisterData
import javax.inject.Inject


class ImplHrDataSource @Inject constructor(private val hrApiService: HrApiService) :
    IntHrDataSource {
    override suspend fun register(data: RegisterData): Result<ModelRegister> {
        return try {
            val dataSource = hrApiService.register(
                data.email ?: "",
                data.password ?: "",
                data.firstName ?: "",
                data.lastName ?: "",
                data.gender ?: "",
                data.specialist ?: "",
                data.birthday ?: "",
                data.status ?: "",
                data.address ?: "",
                data.mobile ?: "",
                data.type ?: ""
            )
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getUserType(type: String, name: String): Result<ModelUserType> {
        return try {
            val data = hrApiService.getUsers(type, name)
            Result.Success(data)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}