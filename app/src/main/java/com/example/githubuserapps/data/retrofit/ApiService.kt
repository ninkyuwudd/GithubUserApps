package com.example.githubuserapps.data.retrofit

import com.example.githubuserapps.data.response.UserAccountResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getDataAccount(
        @Query("q")q:String
    ): Call<UserAccountResponse>
}