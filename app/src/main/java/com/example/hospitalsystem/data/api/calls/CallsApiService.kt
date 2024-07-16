package com.example.hospitalsystem.data.api.calls

import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.calls.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CallsApiService {

    @GET("calls")
    suspend fun getAllCalls(@Query("date") date: String): ModelAllCalls

    @FormUrlEncoded
    @POST("calls")
    suspend fun createCall(
        @Field("patient_name") name: String,
        @Field("age") age: String,
        @Field("doctor_id") doctorId: Int,
        @Field("phone") phone: String,
        @Field("description") description: String
    ): Call

    @GET("doctors")
    suspend fun getDoctors(
        @Query("type") type: String, @Query("name") name: String
    ): ModelUserType
}