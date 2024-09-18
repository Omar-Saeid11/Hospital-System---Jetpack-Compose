package com.example.hospitalsystem.data.api.tasks

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import com.example.hospitalsystem.data.models.tasks.ModelAllTasks
import com.example.hospitalsystem.data.models.tasks.showTask.ModelShowTask
import javax.inject.Inject

class ImplTasksDataSource @Inject constructor(private val tasksApiService: TasksApiService) :
    IntTasksDataSource {
    override suspend fun getAllTasks(): Result<ModelAllTasks> {
        return try {
            val dataSource = tasksApiService.getAllTasks()
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTasksByDate(date: String): Result<ModelAllTasks> {
        return try {
            val dataSource = tasksApiService.getTasksByDate(date)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDos: List<String>
    ): Result<ModelCreateResponse> {
        return try {
            val dataSource = tasksApiService.createTask(userId, taskName, description, toDos)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun executeTask(taskId: Int, note: String): Result<ModelCreateResponse> {
        return try {
            val dataSource = tasksApiService.executeTask(taskId, note)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun showTask(taskId: Int): Result<ModelShowTask> {
        return try {
            val dataSource = tasksApiService.showTask(taskId)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}