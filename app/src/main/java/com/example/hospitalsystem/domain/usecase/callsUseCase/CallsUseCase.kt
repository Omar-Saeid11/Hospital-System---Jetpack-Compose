package com.example.hospitalsystem.domain.usecase.callsUseCase

import com.example.hospitalsystem.domain.entities.CallData
import com.example.hospitalsystem.domain.repository.callsRepo.IntCallsRepository
import javax.inject.Inject

class CallsUseCase @Inject constructor(private val intCallsRepository: IntCallsRepository) {
    suspend fun getAllCalls(date: String) = intCallsRepository.getAllCalls(date)

    suspend fun createCall(call: CallData) = intCallsRepository.createCall(call)

    suspend fun getDoctors(type: String, name: String) = intCallsRepository.getDoctors(type, name)

    suspend fun showCall(id: Int) = intCallsRepository.showCall(id)

    suspend fun logout(id: Int) = intCallsRepository.logout(id)
}