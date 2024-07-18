package com.example.hospitalsystem.domain.usecase.reportUseCase

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.domain.models.report.DomainReport
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.repository.reportRepo.IntReportRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReportUseCase @Inject constructor(private val intReportRepository: IntReportRepository) {
    suspend fun getReports(): Flow<Result<DomainReportResponse>> = intReportRepository.getReports()
    suspend fun getReportsByDate(date: String): Flow<Result<DomainReportResponse>> =
        intReportRepository.getReportsByDate(date)
}