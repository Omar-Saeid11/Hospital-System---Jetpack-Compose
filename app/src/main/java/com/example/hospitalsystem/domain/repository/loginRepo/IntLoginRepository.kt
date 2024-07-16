package com.example.hospitalsystem.domain.repository.loginRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.login.DomainModelLogin
import kotlinx.coroutines.flow.Flow

interface IntLoginRepository {
    suspend fun login(email: String, password: String, deviceToken: String): Flow<Result<DomainModelLogin>>

}