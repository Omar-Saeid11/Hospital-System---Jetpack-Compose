package com.example.hospitalsystem.domain.repository.hrRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.RegisterData
import com.example.hospitalsystem.domain.models.hr.register.DomainModelRegister
import com.example.hospitalsystem.domain.models.hr.userType.DomainModelUserType
import kotlinx.coroutines.flow.Flow

interface IntHrRepository {
    suspend fun register(data: RegisterData): Flow<Result<DomainModelRegister>>
    suspend fun getUserType(type: String, name: String): Flow<Result<DomainModelUserType>>
}