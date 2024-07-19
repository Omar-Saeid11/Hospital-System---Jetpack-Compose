package com.example.hospitalsystem.data.repositories.reportRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.reports.IntReportDataSource
import com.example.hospitalsystem.data.mapper.reportDataToDomain.toDomainModelCreateReport
import com.example.hospitalsystem.data.mapper.reportDataToDomain.toDomainModelShowReport
import com.example.hospitalsystem.data.mapper.reportDataToDomain.toDomainReportResponse
import com.example.hospitalsystem.domain.models.report.DomainReportResponse
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateReport
import com.example.hospitalsystem.domain.models.report.showReport.DomainModelShowReport
import com.example.hospitalsystem.domain.repository.reportRepo.IntReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImplReportRepository(private val intReportDataSource: IntReportDataSource) :
    IntReportRepository {
    override suspend fun getReports(): Flow<Result<DomainReportResponse>> {
        return flow {
            emit(Result.Loading)
            when (val result = intReportDataSource.getReports()) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainReportResponse()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }

    override suspend fun getReportsByDate(date: String): Flow<Result<DomainReportResponse>> {
        return flow {
            emit(Result.Loading)
            when (val result = intReportDataSource.getReportsByDate(date)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainReportResponse()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }

    override suspend fun createReport(
        name: String,
        description: String
    ): Flow<Result<DomainModelCreateReport>> {
        return flow {
            emit(Result.Loading)
            when (val result = intReportDataSource.createReport(name, description)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelCreateReport()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }

    override suspend fun showReportDetails(id: Int): Flow<Result<DomainModelShowReport>> {
        return flow {
            emit(Result.Loading)
            when (val result = intReportDataSource.showReportDetails(id)) {
                is Result.Success -> {
                    val domainModel = result.data.toDomainModelShowReport()
                    emit(Result.Success(domainModel))
                }

                is Result.Error -> {
                    emit(Result.Error(result.exception))
                }

                is Result.Loading -> {
                    emit(Result.Loading)
                }
            }
        }
    }
}