package com.example.hospitalsystem.domain.models.report.showReport

data class DomainReportData(
    val answer: String?,
    val createdAt: String?,
    val description: String?,
    val id: Int?,
    val manger: DomainManager?,
    val note: String?,
    val reportName: String?,
    val status: String?,
    val user: DomainUser?
)
