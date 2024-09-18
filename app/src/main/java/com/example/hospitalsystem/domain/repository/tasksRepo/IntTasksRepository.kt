package com.example.hospitalsystem.domain.repository.tasksRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateResponse
import com.example.hospitalsystem.domain.models.tasks.DomainModelAllTasks
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainModelShowTask
import kotlinx.coroutines.flow.Flow

interface IntTasksRepository {
    suspend fun getAllTasks(): Flow<Result<DomainModelAllTasks>>
    suspend fun getTasksByDate(date: String): Flow<Result<DomainModelAllTasks>>
    suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDos: List<String>
    ): Flow<Result<DomainModelCreateResponse>>

    suspend fun executeTask(taskId: Int,note:String): Flow<Result<DomainModelCreateResponse>>
    suspend fun showTask(taskId: Int): Flow<Result<DomainModelShowTask>>
}