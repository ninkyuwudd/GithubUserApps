package com.example.githubuserapps.data.retrofit

import com.example.githubuserapps.data.response.DetailUserResponse
import com.example.githubuserapps.data.response.UserAccountResponse
import com.example.githubuserapps.data.response.UserFollowersResponse
import com.example.githubuserapps.data.response.UserFollowersResponseItem
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @Headers("Authorization: token 12345")
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getDataAccount(
        @Query("q")q:String
    ): Call<UserAccountResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUserAccount(
        @Path("username") username:String
    ): Call<DetailUserResponse>


    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUserAccountData(
        @Path("username") username: String
    ): Call<List<UserFollowersResponseItem>>


    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUserAccountData(
        @Path("username") username: String
    ): Call<List<UserFollowersResponseItem>>
}