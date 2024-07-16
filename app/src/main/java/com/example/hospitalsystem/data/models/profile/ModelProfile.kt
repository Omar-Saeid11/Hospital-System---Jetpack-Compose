package com.example.hospitalsystem.data.models.profile


import com.google.gson.annotations.SerializedName

data class ModelProfile(
    @SerializedName("data")
    val `data`: Profile?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)