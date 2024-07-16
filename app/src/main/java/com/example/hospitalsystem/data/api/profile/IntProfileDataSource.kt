package com.example.hospitalsystem.data.api.profile

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.profile.ModelProfile

interface IntProfileDataSource {
    suspend fun getProfile(userId: Int): Result<ModelProfile>
}