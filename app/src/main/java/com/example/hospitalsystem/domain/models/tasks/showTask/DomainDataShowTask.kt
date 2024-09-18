package com.example.hospitalsystem.domain.models.tasks.showTask

data class DomainDataShowTask(
    val createdAt: String?,
    val description: String?,
    val id: Int?,
    val note: Any?,
    val status: String?,
    val taskName: String?,
    val toDo: List<DomainToDo?>?,
    val user: DomainUser?
)
