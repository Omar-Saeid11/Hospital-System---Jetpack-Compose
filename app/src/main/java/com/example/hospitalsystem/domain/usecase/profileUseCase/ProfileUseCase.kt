package com.example.hospitalsystem.domain.usecase.profileUseCase

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.profile.DomainModelProfile
import com.example.hospitalsystem.domain.repository.profileRepo.IntProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val intProfileRepository: IntProfileRepository) {
    suspend fun getProfile(userId: Int): Flow<Result<DomainModelProfile>> =
        intProfileRepository.getProfile(userId)
}