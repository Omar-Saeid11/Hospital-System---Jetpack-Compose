package com.example.hospitalsystem.data.models.hr.getUserType

import com.google.gson.annotations.SerializedName

data class ModelUserType(
    @SerializedName("data") val `data`: List<UserType>,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int
)