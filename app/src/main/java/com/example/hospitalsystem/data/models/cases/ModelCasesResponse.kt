package com.example.hospitalsystem.data.models.cases


import com.google.gson.annotations.SerializedName

data class ModelCasesResponse(
    @SerializedName("data")
    val `data`: List<DataCases?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)