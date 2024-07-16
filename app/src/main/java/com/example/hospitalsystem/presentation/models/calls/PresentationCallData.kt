package com.example.hospitalsystem.presentation.models.calls

data class PresentationCallData(
    val createdAt: String,
    val id: Int,
    val patientName: String,
    val status: String
)