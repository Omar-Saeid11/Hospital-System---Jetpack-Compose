package com.example.hospitalsystem.data.models.tasks.showTask


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("note")
    val note: Any?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("task_name")
    val taskName: String?,
    @SerializedName("to_do")
    val toDo: List<ToDo?>?,
    @SerializedName("user")
    val user: User?
)