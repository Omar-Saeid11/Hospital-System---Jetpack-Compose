package com.example.hospitalsystem.domain.entities

data class RegisterData(
    val email: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val specialist: String?,
    val birthday: String?,
    val status: String?,
    val address: String?,
    val mobile: String?,
    val type: String?
)

