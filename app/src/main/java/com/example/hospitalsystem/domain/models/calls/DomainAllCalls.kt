package com.example.hospitalsystem.domain.models.calls

data class DomainAllCalls(
    val data: List<DomainCallData>,
    val message: String,
    val status: Int
)