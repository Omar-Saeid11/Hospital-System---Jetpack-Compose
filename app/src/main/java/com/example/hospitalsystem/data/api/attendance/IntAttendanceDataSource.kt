package com.example.hospitalsystem.data.api.attendance

import com.example.hospitalsystem.core.Result
import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse

interface IntAttendanceDataSource {
    suspend fun attendance(status: String): Result<ModelCreateResponse>
}