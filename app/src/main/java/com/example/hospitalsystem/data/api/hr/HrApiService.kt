package com.example.hospitalsystem.data.api.hr

import com.example.hospitalsystem.data.models.hr.getUserType.ModelUserType
import com.example.hospitalsystem.data.models.hr.register.ModelRegister
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HrApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") fName: String,
        @Field("last_name") lName: String,
        @Field("gender") gender: String,
        @Field("specialist") specialist: String,
        @Field("birthday") birthday: String,
        @Field("status") status: String,
        @Field("address") address: String,
        @Field("mobile") mobile: String,
        @Field("type") type: String
    ): ModelRegister

    @GET("doctors")
    suspend fun getUsers(
        @Query("type") type: String, @Query("name") name: String
    ): ModelUserType
}