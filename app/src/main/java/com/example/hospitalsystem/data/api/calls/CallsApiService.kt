package com.example.hospitalsystem.data.api.calls

import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.calls.ModelAllCalls
import com.example.hospitalsystem.data.models.calls.ModelLogout
import com.example.hospitalsystem.data.models.calls.showCall.ModelCallDetails
import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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

    @GET("calls/{id}")
    suspend fun getCallDetails(@Path("id") id: Int): ModelCallDetails

    @PUT("calls/{id}")
    suspend fun logout(@Path("id") id: Int): ModelLogout
}