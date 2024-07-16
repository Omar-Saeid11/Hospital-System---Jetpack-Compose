package com.example.hospitalsystem.domain.usecase.hrUserCase

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.entities.RegisterData
import com.example.hospitalsystem.domain.models.hr.register.DomainModelRegister
import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HrRegisterUseCase @Inject constructor(private val intHrRepository: IntHrRepository) {
    suspend fun register(data: RegisterData): Flow<Result<DomainModelRegister>> =
        intHrRepository.register(data)
}