package com.example.hospitalsystem.data.models.calls


import com.google.gson.annotations.SerializedName

data class ModelLogout(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)