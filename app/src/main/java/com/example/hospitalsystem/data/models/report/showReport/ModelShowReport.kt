package com.example.hospitalsystem.data.models.report.showReport


import com.google.gson.annotations.SerializedName

data class ModelShowReport(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)