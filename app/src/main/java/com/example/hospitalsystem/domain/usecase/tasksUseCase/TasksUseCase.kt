package com.example.hospitalsystem.domain.usecase.tasksUseCase

import com.example.hospitalsystem.domain.repository.tasksRepo.IntTasksRepository
import javax.inject.Inject

class TasksUseCase @Inject constructor(private val intTasksRepository: IntTasksRepository) {
    suspend fun getAllTasks() = intTasksRepository.getAllTasks()
    suspend fun getTasksByDate(date: String) = intTasksRepository.getTasksByDate(date)
    suspend fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDos: List<String>
    ) = intTasksRepository.createTask(userId, taskName, description, toDos)

    suspend fun executeTask(taskId: Int, note: String) =
        intTasksRepository.executeTask(taskId, note)

    suspend fun showTask(taskId: Int) = intTasksRepository.showTask(taskId)
}