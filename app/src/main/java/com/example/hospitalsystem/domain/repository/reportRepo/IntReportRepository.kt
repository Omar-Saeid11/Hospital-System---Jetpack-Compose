package com.example.hospitalsystem.domain.repository.reportRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateReport
import com.example.hospitalsystem.domain.models.report.showReport.DomainModelShowReport
import kotlinx.coroutines.flow.Flow

interface IntReportRepository {
    suspend fun getReports(): Flow<Result<DomainReportResponse>>
    suspend fun getReportsByDate(date: String): Flow<Result<DomainReportResponse>>
    suspend fun createReport(
        name: String,
        description: String
    ): Flow<Result<DomainModelCreateReport>>

    suspend fun showReportDetails(id: Int): Flow<Result<DomainModelShowReport>>
}