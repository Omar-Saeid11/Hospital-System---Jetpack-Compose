package com.example.hospitalsystem.data.mapper.reportDataToDomain

import com.example.hospitalsystem.data.models.report.Report
import com.example.hospitalsystem.data.models.report.ReportResponse
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import com.example.hospitalsystem.data.models.report.showReport.Data
import com.example.hospitalsystem.data.models.report.showReport.Manger
import com.example.hospitalsystem.data.models.report.showReport.ModelShowReport
import com.example.hospitalsystem.data.models.report.showReport.User
import com.example.hospitalsystem.domain.models.report.DomainReport
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateResponse
import com.example.hospitalsystem.domain.models.report.showReport.DomainManager
import com.example.hospitalsystem.domain.models.report.showReport.DomainModelShowReport
import com.example.hospitalsystem.domain.models.report.showReport.DomainReportData
import com.example.hospitalsystem.domain.models.report.showReport.DomainUser

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


fun ModelCreateResponse.toDomainModelCreateReport(): DomainModelCreateResponse {
    return DomainModelCreateResponse(
        message, status
    )
}

fun ModelShowReport.toDomainModelShowReport(): DomainModelShowReport {
    return DomainModelShowReport(
        data?.toDomainReportData(), message, status
    )
}

fun Data.toDomainReportData(): DomainReportData {
    return DomainReportData(
        answer,
        createdAt,
        description,
        id,
        manger?.toDomainManager(),
        note,
        reportName,
        status,
        user?.toDomainUser()
    )
}

fun Manger.toDomainManager(): DomainManager {
    return DomainManager(
        firstName, id, lastName, specialist, updatedAt
    )
}

fun User.toDomainUser(): DomainUser {
    return DomainUser(
        firstName, id, lastName, specialist
    )
}