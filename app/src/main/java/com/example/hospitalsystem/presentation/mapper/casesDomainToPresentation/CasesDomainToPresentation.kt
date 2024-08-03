package com.example.hospitalsystem.presentation.mapper.casesDomainToPresentation

import com.example.hospitalsystem.domain.models.cases.DomainDataCases
import com.example.hospitalsystem.domain.models.cases.DomainModelCases
import com.example.hospitalsystem.domain.models.cases.showCase.DomainDataShowCase
import com.example.hospitalsystem.domain.models.cases.showCase.DomainShowCaseResponse
import com.example.hospitalsystem.presentation.models.cases.PresentationDataCases
import com.example.hospitalsystem.presentation.models.cases.PresentationModelCases
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationDataShowCase
import com.example.hospitalsystem.presentation.models.cases.showCase.PresentationShowCaseResponse

fun DomainDataCases.toPresentationDataCases(): PresentationDataCases {
    return PresentationDataCases(
        createdAt, id, patientName
    )
}

fun DomainModelCases.toPresentationModelCases(): PresentationModelCases {
    return PresentationModelCases(
        data?.map { it?.toPresentationDataCases() }, message, status
    )
}

fun DomainDataShowCase.toPresentationDataCases(): PresentationDataShowCase {
    return PresentationDataShowCase(
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

fun DomainShowCaseResponse.toPresentationShowCaseResponse(): PresentationShowCaseResponse {
    return PresentationShowCaseResponse(
        data?.toPresentationDataCases(), message, status
    )
}