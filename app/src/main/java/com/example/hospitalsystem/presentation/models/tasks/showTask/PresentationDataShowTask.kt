package com.example.hospitalsystem.presentation.models.tasks.showTask

data class PresentationDataShowTask(
    val createdAt: String?,
    val description: String?,
    val id: Int?,
    val note: Any?,
    val status: String?,
    val taskName: String?,
    val toDo: List<PresentationToDo?>?,
    val user: PresentationUser?
)
