package com.example.hospitalsystem.domain.models.tasks

data class DomainModelAllTasks(
    val `data`: List<DomainData?>?,
    val message: String?,
    val status: Int?
)
