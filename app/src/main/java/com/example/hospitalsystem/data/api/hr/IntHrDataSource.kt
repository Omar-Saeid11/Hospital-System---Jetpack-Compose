package com.example.hospitalsystem.data.api.hr

import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.hr.register.ModelRegister
import com.example.hospitalsystem.domain.entities.RegisterData
import com.example.hospitalsystem.core.Result

interface IntHrDataSource {
    suspend fun register(data: RegisterData): Result<ModelRegister>
    suspend fun getUserType(type: String, name: String): Result<ModelUserType>
}