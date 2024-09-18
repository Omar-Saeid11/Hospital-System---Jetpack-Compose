package com.example.hospitalsystem.data.models.tasks.showTask


import com.google.gson.annotations.SerializedName

data class ModelShowTask(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)