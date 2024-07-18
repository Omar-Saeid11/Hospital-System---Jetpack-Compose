package com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation

import com.example.hospitalsystem.domain.models.report.DomainReport
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.presentation.models.report.PresentationReport
import com.example.hospitalsystem.presentation.models.report.PresentationReportResponse


fun DomainReport.toPresentationReport(): PresentationReport {
    return PresentationReport(
        createdAt, id, reportName, status
    )
}

fun DomainReportResponse.toPresentationReportResponse(): PresentationReportResponse {
    return PresentationReportResponse(
        data?.map { it?.toPresentationReport() }, message, status
    )
}