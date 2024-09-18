package com.example.hospitalsystem.data.api.tasks

import com.example.hospitalsystem.data.models.report.createReport.ModelCreateResponse
import com.example.hospitalsystem.data.models.tasks.ModelAllTasks
import com.example.hospitalsystem.data.models.tasks.showTask.ModelShowTask
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TasksApiService {
    @GET("tasks")
    suspend fun getAllTasks(): ModelAllTasks

    @GET("tasks")
    suspend fun getTasksByDate(@Query("date") date: String): ModelAllTasks

    @FormUrlEncoded
    @POST("tasks")
    suspend fun createTask(
        @Field("user_id") userId: Int,
        @Field("task_name") taskName: String,
        @Field("description") description: String,
        @Field("todos[]") todoList: List<String>
    ): ModelCreateResponse

    @FormUrlEncoded
    @PUT("tasks/{id}")
    suspend fun executeTask(@Path("id") id: Int, @Field("note") note: String): ModelCreateResponse

    @GET("tasks/{id}")
    suspend fun showTask(@Path("id") id: Int): ModelShowTask
}