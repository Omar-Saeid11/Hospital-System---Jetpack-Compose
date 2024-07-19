package com.example.hospitalsystem.data.models.report.showReport


import com.google.gson.annotations.SerializedName

data class Manger(
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("specialist")
    val specialist: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)