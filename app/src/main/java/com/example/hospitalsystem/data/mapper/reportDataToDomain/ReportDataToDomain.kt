package com.example.hospitalsystem.data.mapper.reportDataToDomain

import com.example.hospitalsystem.data.models.report.Report
import com.example.hospitalsystem.data.models.report.ReportResponse
import com.example.hospitalsystem.domain.models.report.DomainReport
import com.example.hospitalsystem.domain.models.report.DomainReportResponse

fun Report.toDomainReport(): DomainReport {
    return DomainReport(
        createdAt, id, reportName, status
    )
}

fun ReportResponse.toDomainReportResponse(): DomainReportResponse {
    return DomainReportResponse(
        this.data?.map { it?.toDomainReport() }, message, status
    )
}

