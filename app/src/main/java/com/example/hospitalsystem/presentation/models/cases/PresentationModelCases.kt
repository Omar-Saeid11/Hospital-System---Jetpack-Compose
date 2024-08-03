package com.example.hospitalsystem.presentation.models.cases

import com.example.hospitalsystem.domain.models.cases.DomainDataCases

data class PresentationModelCases(
    val `data`: List<PresentationDataCases?>?,
    val message: String?,
    val status: Int?
)
