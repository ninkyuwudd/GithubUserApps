package com.example.githubuserapps.ui

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapps.DetailAccount
import com.example.githubuserapps.data.response.DetailUserResponse
import com.example.githubuserapps.data.response.UserAccountResponse
import com.example.githubuserapps.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class DetailViewModel: ViewModel() {


//

    private val _detailDataAccount = MutableLiveData<DetailUserResponse>()
    val DetailDataAccount : LiveData<DetailUserResponse> = _detailDataAccount

    private val _isloading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isloading

//    init {
//
//        findDetailUsernameAccount("username")
//    }

    fun findDetailUsernameAccount(data:String){
        _isloading.value = true
        val client = ApiConfig.getApiService().getDetailUserAccount(data)
        client.enqueue(object : retrofit2.Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ){
                _isloading.value = false
                if(response.isSuccessful){
                    _detailDataAccount.value = response.body()
                }else{
                    Log.e(ContentValues.TAG,"onFilure ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}