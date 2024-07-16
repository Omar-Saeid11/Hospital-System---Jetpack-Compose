package com.example.hospitalsystem.domain.repository.profileRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.profile.DomainModelProfile
import kotlinx.coroutines.flow.Flow

interface IntProfileRepository {
    suspend fun getProfile(userId: Int): Flow<Result<DomainModelProfile>>
}