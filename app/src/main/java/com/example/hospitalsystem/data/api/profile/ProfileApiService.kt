package com.example.hospitalsystem.data.api.profile

import com.example.hospitalsystem.data.models.profile.ModelProfile
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileApiService {
    @POST("show-profile")
    suspend fun getProfile(@Query("user_id") userId: Int): ModelProfile
}