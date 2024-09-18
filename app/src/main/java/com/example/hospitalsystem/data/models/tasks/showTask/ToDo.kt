package com.example.hospitalsystem.data.models.tasks.showTask


import com.google.gson.annotations.SerializedName

data class ToDo(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("task_id")
    val taskId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)