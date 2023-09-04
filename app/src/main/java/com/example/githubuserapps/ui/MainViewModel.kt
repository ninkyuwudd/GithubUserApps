package com.example.githubuserapps.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapps.ActivitySearchMenu
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.data.response.UserAccountResponse
import com.example.githubuserapps.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel(){

//    private val _githubusername = MutableLiveData<ItemsItem>()
//    val githubusername : LiveData<ItemsItem> = _githubusername

    private  val _listUsername = MutableLiveData<List<ItemsItem>>()
    val listReview: LiveData<List<ItemsItem>> = _listUsername

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    companion object {
        private const val USER = "Username"
        private const val USERNAME_ACCOUNT = "fuadmuhammad"
    }

    init {
        findUsernameAccount()
    }

    private fun findUsernameAccount(){
        _isloading.value = true
        val client = ApiConfig.getApiService().getDataAccount(USERNAME_ACCOUNT)
        client.enqueue(object  : retrofit2.Callback<UserAccountResponse>{
            override fun onResponse(
                call: Call<UserAccountResponse>,
                response: Response<UserAccountResponse>
            ){
                _isloading.value = false
                if(response.isSuccessful){
                        _listUsername.value = response.body()?.items

                }else{
                    Log.e(ContentValues.TAG,"onFilure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserAccountResponse>, t: Throwable){
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}