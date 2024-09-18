package com.example.hospitalsystem.data.models.tasks.showTask


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("address")
    val address: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("specialist")
    val specialist: String?
)