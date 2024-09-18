package com.example.hospitalsystem.data.repositories.loginRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.login.IntLoginDataSource
import com.example.hospitalsystem.data.mapper.loginDataToDomain.toDomainModelLogin
import com.example.hospitalsystem.domain.models.login.DomainModelLogin
import com.example.hospitalsystem.domain.repository.loginRepo.IntLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class ImplLoginRepository constructor(private val intLoginDataSource: IntLoginDataSource) :
    IntLoginRepository {
    override suspend fun login(
        email: String,
        password: String,
        deviceToken: String
    ): Flow<Result<DomainModelLogin>> {
        return flow {
            emit(Result.Loading)
            when (val result = intLoginDataSource.login(email, password, deviceToken)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelLogin()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { exception ->
            emit(Result.Error(exception))
        }
    }
}