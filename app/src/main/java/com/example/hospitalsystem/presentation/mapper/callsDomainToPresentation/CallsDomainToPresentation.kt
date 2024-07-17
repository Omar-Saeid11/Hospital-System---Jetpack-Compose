package com.example.hospitalsystem.presentation.mapper.callsDomainToPresentation

import com.example.hospitalsystem.domain.models.calls.DomainAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.calls.DomainCallData
import com.example.hospitalsystem.domain.models.calls.DomainLogout
import com.example.hospitalsystem.domain.models.calls.showCall.DomainCallDetails
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls
import com.example.hospitalsystem.presentation.models.calls.PresentationCall
import com.example.hospitalsystem.presentation.models.calls.PresentationCallData
import com.example.hospitalsystem.presentation.models.calls.PresentationLogout
import com.example.hospitalsystem.presentation.models.calls.showCall.PresentationCallDetails

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

fun DomainCallDetails.toPresentation(): PresentationCallDetails {
    return PresentationCallDetails(
        data = this.data?.toPresentation(),
        message = this.message,
        status = this.status
    )
}

fun com.example.hospitalsystem.domain.models.calls.showCall.DomainCallData.toPresentation(): com.example.hospitalsystem.presentation.models.calls.showCall.PresentationCallData {
    return com.example.hospitalsystem.presentation.models.calls.showCall.PresentationCallData(
        age = this.age,
        analysisId = this.analysisId,
        bloodPressure = this.bloodPressure,
        caseStatus = this.caseStatus,
        createdAt = this.createdAt,
        description = this.description,
        docId = this.docId,
        doctorId = this.doctorId,
        fluidBalance = this.fluidBalance,
        heartRate = this.heartRate,
        id = this.id,
        image = this.image,
        measurementNote = this.measurementNote,
        medicalRecordNote = this.medicalRecordNote,
        nurseId = this.nurseId,
        patientName = this.patientName,
        phone = this.phone,
        respiratoryRate = this.respiratoryRate,
        status = this.status,
        sugarAnalysis = this.sugarAnalysis,
        temperature = this.temperature
    )
}

fun DomainLogout.toPresentation(): PresentationLogout {
    return PresentationLogout(
        message = this.message,
        status = this.status
    )
}

