package com.example.hospitalsystem.data.repositories.hrRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.hr.IntHrDataSource
import com.example.hospitalsystem.data.mapper.hrDataToDomain.toDomainModel
import com.example.hospitalsystem.domain.entities.RegisterData
import com.example.hospitalsystem.domain.models.hr.register.DomainModelRegister
import com.example.hospitalsystem.domain.models.hr.userType.DomainModelUserType
import com.example.hospitalsystem.domain.repository.hrRepo.IntHrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class ImpHrRepository constructor(private val intHrDataSource: IntHrDataSource) : IntHrRepository {
    override suspend fun register(data: RegisterData): Flow<Result<DomainModelRegister>> {
        return flow {
            emit(Result.Loading)
            when (val result = intHrDataSource.register(data)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModel()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    // Handle if necessary
                }
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { exception ->
            emit(Result.Error(exception))
        }
    }

    override suspend fun getUserType(
        type: String,
        name: String
    ): Flow<Result<DomainModelUserType>> {
        return flow {
            emit(Result.Loading)
            when (val result = intHrDataSource.getUserType(type, name)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModel()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    // Handle if necessary
                }
            }
        }.onStart {
            emit(Result.Loading)
        }.catch { exception ->
            emit(Result.Error(exception))
        }
    }
}
