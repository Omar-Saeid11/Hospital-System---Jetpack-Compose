package com.example.hospitalsystem.domain.usecase.reportUseCase

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateReport
import com.example.hospitalsystem.domain.models.report.showReport.DomainModelShowReport
import com.example.hospitalsystem.domain.repository.reportRepo.IntReportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReportUseCase @Inject constructor(private val intReportRepository: IntReportRepository) {
    suspend fun getReports(): Flow<Result<DomainReportResponse>> = intReportRepository.getReports()
    suspend fun getReportsByDate(date: String): Flow<Result<DomainReportResponse>> =
        intReportRepository.getReportsByDate(date)

    suspend fun createReport(
        name: String,
        description: String
    ): Flow<Result<DomainModelCreateReport>> =
        intReportRepository.createReport(name, description)

    suspend fun showReportDetails(id: Int): Flow<Result<DomainModelShowReport>> =
        intReportRepository.showReportDetails(id)
}