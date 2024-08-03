package com.example.hospitalsystem.data.repositories.casesRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.cases.IntCasesDataSource
import com.example.hospitalsystem.data.mapper.callsDataToDomain.toDomainCall
import com.example.hospitalsystem.data.mapper.casesToDomain.toDomainModelCases
import com.example.hospitalsystem.data.mapper.casesToDomain.toDomainShowCaseResponse
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.cases.DomainModelCases
import com.example.hospitalsystem.domain.models.cases.showCase.DomainShowCaseResponse
import com.example.hospitalsystem.domain.repository.casesRepo.IntCasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImplCasesRepository constructor(private val intCasesDataSource: IntCasesDataSource) :
    IntCasesRepository {
    override suspend fun getAllCases(): Flow<Result<DomainModelCases>> {
        return flow {
            emit(Result.Loading)
            when (val result = intCasesDataSource.getAllCases()) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelCases()
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

    override suspend fun showCase(id: Int): Flow<Result<DomainShowCaseResponse>> {
        return flow {
            emit(Result.Loading)
            when (val result = intCasesDataSource.showCase(id)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainShowCaseResponse()
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

    override suspend fun makeRequest(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ): Flow<Result<DomainCall>> {
        return flow {
            emit(Result.Loading)
            when (val result = intCasesDataSource.makeRequest(callId, userId, note, types)) {
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
}