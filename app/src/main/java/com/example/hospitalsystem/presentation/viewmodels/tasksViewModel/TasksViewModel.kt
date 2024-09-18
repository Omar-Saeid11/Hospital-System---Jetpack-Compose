package com.example.hospitalsystem.presentation.viewmodels.tasksViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.usecase.tasksUseCase.TasksUseCase
import com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation.toPresentationModelCreateReport
import com.example.hospitalsystem.presentation.mapper.taskDomainToPresentation.toPresentation
import com.example.hospitalsystem.presentation.models.report.createReport.PresentationModelCreateReport
import com.example.hospitalsystem.presentation.models.tasks.PresentationModelAllTasks
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationModelShowTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val tasksUseCase: TasksUseCase) : ViewModel() {
    private val _allTasksState = MutableStateFlow<Result<PresentationModelAllTasks>>(Result.Loading)
    val allTasksState: StateFlow<Result<PresentationModelAllTasks>> = _allTasksState

    private val _createTaskState =
        MutableStateFlow<Result<PresentationModelCreateReport>>(Result.Loading)
    val createTaskState: StateFlow<Result<PresentationModelCreateReport>> = _createTaskState

    private val _showTaskState = MutableStateFlow<Result<PresentationModelShowTask>>(Result.Loading)
    val showTaskState: StateFlow<Result<PresentationModelShowTask>> = _showTaskState


    fun getAllTasks() {
        viewModelScope.launch {
            tasksUseCase.getAllTasks()
                .catch { e -> _allTasksState.value = Result.Error(e) }
                .collect { result ->
                    _allTasksState.value = when (result) {
                        is Result.Success -> {
                            Result.Success(result.data.toPresentation())
                        }

                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun getTasksByDate(date: String) {
        viewModelScope.launch {
            tasksUseCase.getTasksByDate(date)
                .catch { e -> _allTasksState.value = Result.Error(e) }
                .collect { result ->
                    _allTasksState.value = when (result) {
                        is Result.Success -> {
                            Result.Success(result.data.toPresentation())
                        }

                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun createTask(
        userId: Int,
        taskName: String,
        description: String,
        toDos: List<String>
    ) {
        viewModelScope.launch {
            tasksUseCase.createTask(userId, taskName, description, toDos)
                .catch { e -> _createTaskState.value = Result.Error(e) }
                .collect { result ->
                    _createTaskState.value = when (result) {
                        is Result.Success -> {
                            Result.Success(result.data.toPresentationModelCreateReport())
                        }

                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun executeTask(taskId: Int, note: String) {
        viewModelScope.launch {
            tasksUseCase.executeTask(taskId, note)
                .catch { e -> _createTaskState.value = Result.Error(e) }
                .collect { result ->
                    _createTaskState.value = when (result) {
                        is Result.Success -> {
                            Result.Success(result.data.toPresentationModelCreateReport())
                        }

                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }

    fun showTask(taskId: Int) {
        viewModelScope.launch {
            tasksUseCase.showTask(taskId)
                .catch { e -> _showTaskState.value = Result.Error(e) }
                .collect { result ->
                    _showTaskState.value = when (result) {
                        is Result.Success -> {
                            Result.Success(result.data.toPresentation())
                        }

                        is Result.Error -> Result.Error(result.exception)
                        is Result.Loading -> Result.Loading
                    }
                }
        }
    }
}