package com.example.hospitalsystem.data.api.tasks

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import com.example.hospitalsystem.data.models.tasks.ModelAllTasks
import com.example.hospitalsystem.data.models.tasks.showTask.ModelShowTask

interface IntTasksDataSource {
    suspend fun getAllTasks(): Result<ModelAllTasks>
    suspend fun getTasksByDate(date: String): Result<ModelAllTasks>
    suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDos: List<String>
    ): Result<ModelCreateResponse>

    suspend fun executeTask(taskId: Int,note:String): Result<ModelCreateResponse>
    suspend fun showTask(taskId: Int): Result<ModelShowTask>
}