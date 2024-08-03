package com.example.hospitalsystem.domain.models.cases.showCase

data class DomainShowCaseResponse(
    val `data`: DomainDataShowCase?,
    val message: String?,
    val status: Int?
)
