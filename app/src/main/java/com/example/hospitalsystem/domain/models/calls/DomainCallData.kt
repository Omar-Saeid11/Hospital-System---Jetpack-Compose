package com.example.hospitalsystem.domain.models.calls

data class DomainCallData(
    val createdAt: String,
    val id: Int,
    val patientName: String,
    val status: String
)