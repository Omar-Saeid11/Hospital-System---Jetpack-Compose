package com.example.hospitalsystem.data.repositories.callsRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.calls.IntCallsDataSource
import com.example.hospitalsystem.data.mapper.callsDataToDomain.toDomainAllCalls
import com.example.hospitalsystem.data.mapper.callsDataToDomain.toDomainCall
import com.example.hospitalsystem.data.mapper.hrDataToDomain.toDomainModel
import com.example.hospitalsystem.domain.entities.CallData
import com.example.hospitalsystem.domain.models.calls.DomainAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.hr.userType.DomainModelUserType
import com.example.hospitalsystem.domain.repository.callsRepo.IntCallsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImplCallsRepository constructor(private val intCallsDataSource: IntCallsDataSource) :
    IntCallsRepository {
    override suspend fun getAllCalls(date: String): Flow<Result<DomainAllCalls>> {
        return flow {
            emit(Result.Loading)
            when (val result = intCallsDataSource.getAllCalls(date)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainAllCalls()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }

    override suspend fun createCall(call: CallData): Flow<Result<DomainCall>> {
        return flow {
            emit(Result.Loading)
            when (val result = intCallsDataSource.createCall(call)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainCall()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }

    override suspend fun getDoctors(type: String, name: String): Flow<Result<DomainModelUserType>> {
        return flow {
            emit(Result.Loading)
            when (val result = intCallsDataSource.getDoctors(type, name)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModel()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }

}