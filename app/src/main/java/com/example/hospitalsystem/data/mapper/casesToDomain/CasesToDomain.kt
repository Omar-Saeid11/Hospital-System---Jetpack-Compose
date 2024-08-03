package com.example.hospitalsystem.data.mapper.casesToDomain

import com.example.hospitalsystem.data.models.cases.DataCases
import com.example.hospitalsystem.data.models.cases.ModelCasesResponse
import com.example.hospitalsystem.data.models.cases.showCase.DataShowCase
import com.example.hospitalsystem.data.models.cases.showCase.ShowCaseResponse
import com.example.hospitalsystem.domain.models.cases.DomainDataCases
import com.example.hospitalsystem.domain.models.cases.DomainModelCases
import com.example.hospitalsystem.domain.models.cases.showCase.DomainDataShowCase
import com.example.hospitalsystem.domain.models.cases.showCase.DomainShowCaseResponse

fun DataCases.toDomainDataCases(): DomainDataCases {
    return DomainDataCases(
        createdAt, id, patientName
    )
}

fun ModelCasesResponse.toDomainModelCases(): DomainModelCases {
    return DomainModelCases(
        data?.map { it?.toDomainDataCases() }, message, status
    )
}

fun DataShowCase.toDomainDataCases(): DomainDataShowCase {
    return DomainDataShowCase(
        age,
        analysisId,
        bloodPressure,
        caseStatus,
        createdAt,
        description,
        docId,
        doctorId,
        fluidBalance,
        heartRate,
        id,
        image,
        measurementNote,
        medicalRecordNote,
        nurseId,
        patientName,
        phone,
        respiratoryRate,
        status,
        sugarAnalysis,
        tempreture
    )
}

fun ShowCaseResponse.toDomainShowCaseResponse(): DomainShowCaseResponse {
    return DomainShowCaseResponse(
        data?.toDomainDataCases(), message, status
    )
}