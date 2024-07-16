package com.example.hospitalsystem.data.api.profile

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.profile.ModelProfile
import javax.inject.Inject

class ImplProfileDataSource @Inject constructor(private val profileApiService: ProfileApiService) :
    IntProfileDataSource {
    override suspend fun getProfile(userId: Int): Result<ModelProfile> {
        return try {
            val dataSource = profileApiService.getProfile(userId)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}