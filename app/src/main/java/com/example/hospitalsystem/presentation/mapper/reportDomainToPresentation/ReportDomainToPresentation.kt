package com.example.hospitalsystem.presentation.mapper.reportDomainToPresentation

import com.example.hospitalsystem.domain.models.report.DomainReport
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateResponse
import com.example.hospitalsystem.domain.models.report.showReport.DomainManager
import com.example.hospitalsystem.domain.models.report.showReport.DomainModelShowReport
import com.example.hospitalsystem.domain.models.report.showReport.DomainReportData
import com.example.hospitalsystem.domain.models.report.showReport.DomainUser
import com.example.hospitalsystem.presentation.models.report.PresentationReport
import com.example.hospitalsystem.presentation.models.report.PresentationReportResponse
import com.example.hospitalsystem.presentation.models.report.createReport.PresentationModelCreateReport
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationManager
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationModelShowReport
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationReportData
import com.example.hospitalsystem.presentation.models.report.showReport.PresentationUser


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

fun DomainModelCreateResponse.toPresentationModelCreateReport(): PresentationModelCreateReport {
    return PresentationModelCreateReport(
        message, status
    )
}

fun DomainModelShowReport.toPresentationModelShowReport(): PresentationModelShowReport {
    return PresentationModelShowReport(
        data?.toPresentationReportData(), message, status
    )
}

fun DomainReportData.toPresentationReportData(): PresentationReportData {
    return PresentationReportData(
        answer,
        createdAt,
        description,
        id,
        manger?.toPresentationManager(),
        note,
        reportName,
        status,
        user?.toPresentationUser()
    )
}

fun DomainManager.toPresentationManager(): PresentationManager {
    return PresentationManager(
        firstName, id, lastName, specialist, updatedAt
    )
}

fun DomainUser.toPresentationUser(): PresentationUser {
    return PresentationUser(
        firstName, id, lastName, specialist
    )
}