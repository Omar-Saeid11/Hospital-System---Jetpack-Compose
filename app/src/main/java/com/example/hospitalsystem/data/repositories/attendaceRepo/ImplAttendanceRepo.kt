package com.example.hospitalsystem.data.repositories.attendaceRepo

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.api.attendance.IntAttendanceDataSource
import com.example.hospitalsystem.data.mapper.reportDataToDomain.toDomainModelCreateReport
import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateResponse
import com.example.hospitalsystem.domain.repository.attendanceRepo.IntAttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImplAttendanceRepo constructor(private val intAttendanceDataSource: IntAttendanceDataSource) :
    IntAttendanceRepository {
    override suspend fun attendance(status: String): Flow<Result<DomainModelCreateResponse>> {
        return flow {
            emit(Result.Loading)
            when (val result = intAttendanceDataSource.attendance(status)) {
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

}