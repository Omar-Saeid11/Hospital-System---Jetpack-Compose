package com.example.hospitalsystem.domain.usecase.casesUseCase

import com.example.hospitalsystem.domain.repository.casesRepo.IntCasesRepository
import javax.inject.Inject

class CasesUseCase @Inject constructor(private val intCasesRepository: IntCasesRepository) {
    suspend fun getAllCases() = intCasesRepository.getAllCases()
    suspend fun showCase(id: Int) = intCasesRepository.showCase(id)
    suspend fun makeRequest(
        callId: Int,
        userId: Int,
        note: String,
        types: List<String>
    ) = intCasesRepository.makeRequest(callId, userId, note, types)

    suspend fun addMeasurement(
        caseId: Int,
        bloodPressure: String,
        sugarAnalysis: String,
        tempreture: String,
        fluidBalance: String,
        respiratoryRate: String,
        heartRate: String,
        not: String,
        status: String
    ) = intCasesRepository.addMeasurement(
        caseId,
        bloodPressure,
        sugarAnalysis,
        tempreture,
        fluidBalance,
        respiratoryRate,
        heartRate,
        not,
        status
    )
}