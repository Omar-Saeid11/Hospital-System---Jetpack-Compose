package com.example.hospitalsystem.data.models.tasks


import com.google.gson.annotations.SerializedName

data class ModelAllTasks(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)