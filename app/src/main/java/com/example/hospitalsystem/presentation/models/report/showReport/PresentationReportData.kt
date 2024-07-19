package com.example.hospitalsystem.presentation.models.report.showReport

data class PresentationReportData(
    val answer: String?,
    val createdAt: String?,
    val description: String?,
    val id: Int?,
    val manger: PresentationManager?,
    val note: String?,
    val reportName: String?,
    val status: String?,
    val user: PresentationUser?
)
