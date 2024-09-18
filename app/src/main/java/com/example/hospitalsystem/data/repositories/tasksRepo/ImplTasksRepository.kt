package com.example.hospitalsystem.data.repositories.tasksRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.tasks.IntTasksDataSource
import com.example.hospitalsystem.data.mapper.reportDataToDomain.toDomainModelCreateReport
import com.example.hospitalsystem.data.mapper.tasksDataToDomain.toDomain
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateResponse
import com.example.hospitalsystem.domain.models.tasks.DomainModelAllTasks
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainModelShowTask
import com.example.hospitalsystem.domain.repository.tasksRepo.IntTasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImplTasksRepository(private val tasksDataSource: IntTasksDataSource) : IntTasksRepository {
    override suspend fun getAllTasks(): Flow<Result<DomainModelAllTasks>> {
        return flow {
            emit(Result.Loading)
            when (val result = tasksDataSource.getAllTasks()) {
                is Result.Success -> {
                    val domainModel = result.data.toDomain()
                    emit(Result.Success(domainModel))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }
            }
        }
    }

    override suspend fun getTasksByDate(date: String): Flow<Result<DomainModelAllTasks>> {
        return flow {
            emit(Result.Loading)
            when (val result = tasksDataSource.getTasksByDate(date)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomain()
                    emit(Result.Success(domainModel))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }
            }
        }
    }

    override suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDos: List<String>
    ): Flow<Result<DomainModelCreateResponse>> {
        return flow {
            emit(Result.Loading)
            when (val result = tasksDataSource.createTask(userId, taskName, description, toDos)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelCreateReport()
                    emit(Result.Success(domainModel))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }
            }
        }
    }

    override suspend fun executeTask(
        taskId: Int,
        note: String
    ): Flow<Result<DomainModelCreateResponse>> {
        return flow {
            emit(Result.Loading)
            when (val result = tasksDataSource.executeTask(taskId, note)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelCreateReport()
                    emit(Result.Success(domainModel))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }
            }
        }
    }

    override suspend fun showTask(taskId: Int): Flow<Result<DomainModelShowTask>> {
        return flow {
            emit(Result.Loading)
            when (val result = tasksDataSource.showTask(taskId)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomain()
                    emit(Result.Success(domainModel))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }
            }
        }
    }
}