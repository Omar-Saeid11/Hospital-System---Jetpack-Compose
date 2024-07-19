package com.example.hospitalsystem.data.models.report.showReport


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("answer")
    val answer: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("manger")
    val manger: Manger?,
    @SerializedName("note")
    val note: String?,
    @SerializedName("report_name")
    val reportName: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("user")
    val user: User?
)