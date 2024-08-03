package com.example.hospitalsystem.data.models.cases


import com.google.gson.annotations.SerializedName

data class DataCases(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("patient_name")
    val patientName: String?
)