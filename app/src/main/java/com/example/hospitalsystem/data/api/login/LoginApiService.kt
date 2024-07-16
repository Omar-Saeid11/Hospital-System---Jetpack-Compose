package com.example.hospitalsystem.data.api.login

import com.example.hospitalsystem.data.models.login.ModelLogin
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") deviceToken: String
    ): ModelLogin
}