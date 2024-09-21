package com.example.hospitalsystem.domain.usecase.attendanceUseCase

import com.example.hospitalsystem.domain.repository.attendanceRepo.IntAttendanceRepository
import javax.inject.Inject

class AttendanceUseCase @Inject constructor(private val intAttendanceRepository: IntAttendanceRepository) {
    suspend fun attendance(status: String) = intAttendanceRepository.attendance(status)
}