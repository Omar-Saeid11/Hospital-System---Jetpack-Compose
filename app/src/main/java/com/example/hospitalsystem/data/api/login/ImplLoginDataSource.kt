package com.example.hospitalsystem.data.api.login

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.login.ModelLogin
import javax.inject.Inject

class ImplLoginDataSource @Inject constructor(private val loginApiService: LoginApiService) :
    IntLoginDataSource {
    override suspend fun login(
        email: String,
        password: String,
        deviceToken: String
    ): Result<ModelLogin> {
        return try {
            val dataSource = loginApiService.login(email, password, deviceToken)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}