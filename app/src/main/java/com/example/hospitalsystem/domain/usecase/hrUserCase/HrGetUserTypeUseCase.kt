package com.example.hospitalsystem.domain.usecase.hrUserCase

import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import javax.inject.Inject

class HrGetUserTypeUseCase @Inject constructor(private val intHrRepository: IntHrRepository) {
    suspend fun getUserType(type: String, name: String) =
        intHrRepository.getUserType(type, name)
}