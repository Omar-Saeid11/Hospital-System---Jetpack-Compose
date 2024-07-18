package com.example.hospitalsystem.domain.models.report

data class DomainReportResponse(
    val `data`: List<DomainReport?>?,
    val message: String?,
    val status: Int?
)
