package com.example.hospitalsystem.data.models.hr.register


import com.google.gson.annotations.SerializedName

data class ModelRegister(
    @SerializedName("data")
    val `data`: Register,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)