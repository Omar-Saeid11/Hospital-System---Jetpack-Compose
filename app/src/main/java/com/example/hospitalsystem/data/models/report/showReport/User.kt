package com.example.hospitalsystem.data.models.report.showReport


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("specialist")
    val specialist: String?
)