package com.example.hospitalsystem.data.mapper.callsDataToDomain

import com.example.hospitalsystem.data.models.calls.AllCallsData
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.calls.DomainCallData

fun ModelAllCalls.toDomainAllCalls(): DomainAllCalls {
    return DomainAllCalls(
        data = this.data.map { it.toDomainCallData() },
        message = this.message,
        status = this.status
    )
}

fun AllCallsData.toDomainCallData(): DomainCallData {
    return DomainCallData(
        createdAt = this.createdAt,
        id = this.id,
        patientName = this.patientName,
        status = this.status
    )
}

fun Call.toDomainCall(): DomainCall {
    return DomainCall(
        message = this.message,
        status = this.status
    )
}