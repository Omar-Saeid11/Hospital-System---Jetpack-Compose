package com.example.hospitalsystem.domain.usecase.loginUseCase

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.login.DomainModelLogin
import com.example.hospitalsystem.domain.repository.loginRepo.IntLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val intLoginRepository: IntLoginRepository) {
    suspend fun login(
        email: String,
        password: String,
        deviceToken: String
    ): Flow<Result<DomainModelLogin>> = intLoginRepository.login(email, password, deviceToken)
}
