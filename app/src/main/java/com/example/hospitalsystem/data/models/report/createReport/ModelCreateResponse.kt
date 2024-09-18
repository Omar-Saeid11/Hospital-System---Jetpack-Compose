package com.example.hospitalsystem.data.models.report.createReport


import com.google.gson.annotations.SerializedName

data class ModelCreateResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)