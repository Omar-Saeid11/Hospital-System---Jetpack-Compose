package com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation

import com.example.hospitalsystem.domain.models.calls.DomainAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.calls.DomainCallData
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.models.calls.PresentationCall
import com.example.hospitalsystem.presentation.models.calls.PresentationCallData

fun DomainAllCalls.toPresentationAllCalls(): PresentationAllCalls {
    return PresentationAllCalls(
        data = this.data.map { it.toPresentationCallData() },
        message = this.message,
        status = this.status
    )
}

fun DomainCallData.toPresentationCallData(): PresentationCallData {
    return PresentationCallData(
        createdAt = this.createdAt,
        id = this.id,
        patientName = this.patientName,
        status = this.status
    )
}

fun DomainCall.toPresentationCall(): PresentationCall {
    return PresentationCall(
        message = this.message,
        status = this.status
    )
}