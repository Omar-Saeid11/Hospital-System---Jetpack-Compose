package com.example.hospitalsystem.presentation.models.hr.userType

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PresentationUserType(
    val id: Int,
    val firstName: String,
    val avatar: String,
    val type: String
) : Parcelable
