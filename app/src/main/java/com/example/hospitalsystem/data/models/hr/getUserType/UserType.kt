package com.example.hospitalsystem.data.models.hr.getUserType

import com.google.gson.annotations.SerializedName

data class UserType(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: Int
)

