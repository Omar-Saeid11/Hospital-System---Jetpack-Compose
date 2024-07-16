package com.example.hospitalsystem.data.models.calls


import com.google.gson.annotations.SerializedName

data class ModelAllCalls(
    @SerializedName("data")
    val `data`: List<AllCallsData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)