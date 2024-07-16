package com.example.hospitalsystem.data.repositories.profileRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.profile.IntProfileDataSource
import com.example.hospitalsystem.data.mapper.profileDataToDomain.toDomainModelProfile
import com.example.hospitalsystem.domain.models.profile.DomainModelProfile
import com.example.hospitalsystem.domain.repository.profileRepo.IntProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImplProfileRepository constructor(private val intProfileDataSource: IntProfileDataSource) :
    IntProfileRepository {
    override suspend fun getProfile(userId: Int): Flow<Result<DomainModelProfile>> {
        return flow {
            emit(Result.Loading)
            when (val result = intProfileDataSource.getProfile(userId)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelProfile()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {

                }
            }
        }
    }
}