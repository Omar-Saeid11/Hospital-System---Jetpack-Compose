package com.example.hospitalsystem.domain.models.calls.showCall

data class DomainCallDetails(
    val data: DomainCallData?,
    val message: String?,
    val status: Int?
)