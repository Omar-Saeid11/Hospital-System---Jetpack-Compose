package com.example.hospitalsystem.data.api.cases

import com.example.hospitalsystem.data.models.calls.Call
import com.example.hospitalsystem.data.models.cases.ModelCasesResponse
import com.example.hospitalsystem.data.models.cases.showCase.ShowCaseResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CasesApiService {
    @GET("case")
    suspend fun getAllCases(): ModelCasesResponse

    @GET("case/{id}")
    suspend fun showCase(@Path("id") id: Int): ShowCaseResponse

    @FormUrlEncoded
    @POST("make-request")
    suspend fun makeRequest(
        @Field("call_id") callId: Int,
        @Field("user_id") userId: Int,
        @Field("note") note: String,
        @Field("types[]") types: List<String>
    ): Call

}