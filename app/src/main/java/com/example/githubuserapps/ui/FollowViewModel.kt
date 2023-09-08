package com.example.githubuserapps.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.githubuserapps.data.response.UserAccountResponse
import com.example.githubuserapps.data.response.UserFollowersResponse
import com.example.githubuserapps.data.response.UserFollowersResponseItem
import com.example.githubuserapps.data.retrofit.ApiConfig
import retrofit2.Call

import retrofit2.Response

class FollowViewModel: ViewModel() {

    private val _listFollowUser = MutableLiveData<List<UserFollowersResponseItem>>()
    val listFollow: LiveData<List<UserFollowersResponseItem>> = _listFollowUser

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    private var usernameData: String? = null

    fun setUsernameDataGet(getUsername:String){
        usernameData = getUsername
    }

    init {
        findUsernameFollowAccount(usernameData.toString())
    }

    fun findUsernameFollowAccount(data: String){
        _isloading.value = true
        val client = ApiConfig.getApiService().getFollowersUserAccountData(data)
        client.enqueue(object  : retrofit2.Callback<List<UserFollowersResponseItem>>{

            override fun onResponse(
                call: Call<List<UserFollowersResponseItem>>,
                response: Response<List<UserFollowersResponseItem>>
            ) {
                _isloading.value = false
                if(response.isSuccessful){
                    _listFollowUser.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<UserFollowersResponseItem>>, t: Throwable) {
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}