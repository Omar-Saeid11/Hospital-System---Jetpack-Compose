package com.example.hospitalsystem.domain.repository.attendanceRepo

import com.example.hospitalsystem.domain.models.report.createReport.DomainModelCreateResponse
import kotlinx.coroutines.flow.Flow
import com.example.hospitalsystem.core.Result

interface IntAttendanceRepository {
    suspend fun attendance(status: String): Flow<Result<DomainModelCreateResponse>>
}