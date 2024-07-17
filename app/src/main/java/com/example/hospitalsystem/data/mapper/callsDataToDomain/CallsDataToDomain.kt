package com.example.hospitalsystem.data.mapper.callsDataToDomain

import com.example.hospitalsystem.data.models.calls.AllCallsData
import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.calls.ModelLogout
import com.example.hospitalsystem.data.models.calls.showCall.Data
import com.example.hospitalsystem.data.models.calls.showCall.ModelCallDetails
import com.example.hospitalsystem.domain.models.calls.DomainAllCalls
import com.example.hospitalsystem.domain.models.calls.DomainCall
import com.example.hospitalsystem.domain.models.calls.DomainCallData
import com.example.hospitalsystem.domain.models.calls.DomainLogout
import com.example.hospitalsystem.domain.models.calls.showCall.DomainCallDetails

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

fun ModelCallDetails.toDomain(): DomainCallDetails {
    return DomainCallDetails(
        data = this.data?.toDomain(),
        message = this.message,
        status = this.status
    )
}

fun Data.toDomain(): com.example.hospitalsystem.domain.models.calls.showCall.DomainCallData {
    return com.example.hospitalsystem.domain.models.calls.showCall.DomainCallData(
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
        temperature = this.tempreture
    )
}

fun ModelLogout.toDomain(): DomainLogout {
    return DomainLogout(
        message = this.message ?: "",
        status = this.status ?: 0
    )
}

