package com.example.hospitalsystem.data.api.attendance

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import javax.inject.Inject

class ImplAttendanceDataSource @Inject constructor(private val attendanceApiService: AttendanceApiService) :
    IntAttendanceDataSource {
    override suspend fun attendance(status: String): Result<ModelCreateResponse> {
        return try {
            val dataSource = attendanceApiService.attendance(status)
            Result.Success(dataSource)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}