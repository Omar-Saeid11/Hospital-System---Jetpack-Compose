package com.example.hospitalsystem.data.models.tasks


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("task_name")
    val taskName: String?
)