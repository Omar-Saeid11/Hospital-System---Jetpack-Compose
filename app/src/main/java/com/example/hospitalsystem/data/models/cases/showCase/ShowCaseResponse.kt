package com.example.hospitalsystem.data.models.cases.showCase


import com.google.gson.annotations.SerializedName

data class ShowCaseResponse(
    @SerializedName("data")
    val `data`: DataShowCase?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)