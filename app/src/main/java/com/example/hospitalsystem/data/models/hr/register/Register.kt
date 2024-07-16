package com.example.hospitalsystem.data.models.hr.register


import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("specialist")
    val specialist: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("verified")
    val verified: Boolean?
)