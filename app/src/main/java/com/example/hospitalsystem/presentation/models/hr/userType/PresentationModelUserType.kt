package com.example.hospitalsystem.presentation.models.hr.userType

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PresentationModelUserType(
    val data: List<PresentationUserType>,
    val message: String,
    val status: Int
) : Parcelable
