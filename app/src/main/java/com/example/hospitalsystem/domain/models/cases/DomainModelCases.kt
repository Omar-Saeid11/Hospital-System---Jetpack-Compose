package com.example.hospitalsystem.domain.models.cases

data class DomainModelCases(
    val `data`: List<DomainDataCases?>?,
    val message: String?,
    val status: Int?
)
