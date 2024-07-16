package com.example.hospitalsystem.data.api.login

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.login.ModelLogin

interface IntLoginDataSource {
    suspend fun login(email: String, password: String, deviceToken: String): Result<ModelLogin>
}