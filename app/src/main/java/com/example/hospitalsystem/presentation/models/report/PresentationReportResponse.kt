package com.example.hospitalsystem.presentation.models.report

data class PresentationReportResponse(
    val `data`: List<PresentationReport?>?,
    val message: String?,
    val status: Int?
)
