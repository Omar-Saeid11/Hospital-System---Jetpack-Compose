package com.example.hospitalsystem.domain.repository.reportRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.report.DomainReport
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import kotlinx.coroutines.flow.Flow

interface IntReportRepository {
    suspend fun getReports(): Flow<Result<DomainReportResponse>>
    suspend fun getReportsByDate(date: String): Flow<Result<DomainReportResponse>>
}