package com.example.hospitalsystem.presentation.models.report.createReport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PresentationModelCreateReport(
    val message: String?,
    val status: Int?
): Parcelable