package com.example.hospitalsystem.data.models.report


import com.google.gson.annotations.SerializedName

data class Report(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("report_name")
    val reportName: String?,
    @SerializedName("status")
    val status: String?
)