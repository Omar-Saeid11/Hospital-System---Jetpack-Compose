package com.example.hospitalsystem.data.models.calls


import com.google.gson.annotations.SerializedName

data class AllCallsData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("patient_name")
    val patientName: String,
    @SerializedName("status")
    val status: String
)