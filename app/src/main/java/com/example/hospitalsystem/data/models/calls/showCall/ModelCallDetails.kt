package com.example.hospitalsystem.data.models.calls.showCall


import com.google.gson.annotations.SerializedName

data class ModelCallDetails(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)