package com.example.hospitalsystem.presentation.models.calls

data class PresentationAllCalls(
    val data: List<PresentationCallData>,
    val message: String,
    val status: Int
)