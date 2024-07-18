package com.example.hospitalsystem.data.models.report


import com.google.gson.annotations.SerializedName

data class ReportResponse(
    @SerializedName("data")
    val `data`: List<Report?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)