package com.example.hospitalsystem.domain.entities

data class CallData(
    val patientName: String,
    val age: Int,
    val doctorId: Int,
    val phone: String,
    val description: String
)
