package com.example.githubuserapps

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.data.response.UserAccountResponse
import com.example.githubuserapps.data.retrofit.ApiConfig
import com.example.githubuserapps.databinding.ActivitySearchMenuBinding
import com.example.githubuserapps.ui.ListNameAdapter
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ActivitySearchMenu : AppCompatActivity() {

    private lateinit var binding:ActivitySearchMenuBinding

    companion object {
        private const val USER = "Username"
        private const val USERNAME_ACCOUNT = "sidiqpermana"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            svSearchView.setupWithSearchBar(svSearch)
            svSearchView.editText.setOnEditorActionListener {txtView,actionId,event ->
                svSearch.text = svSearchView.text
                svSearchView.hide()
                Toast.makeText(this@ActivitySearchMenu, svSearchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }


        findUsernameAccount()
    }

    private fun findUsernameAccount(){
        showLoading(true)
        val client = ApiConfig.getApiService().getDataAccount(USERNAME_ACCOUNT)
        client.enqueue(object  : retrofit2.Callback<UserAccountResponse>{
            override fun onResponse(
                call: Call<UserAccountResponse>,
                response: Response<UserAccountResponse>
            ){
                showLoading(false)
                if(response.isSuccessful){
                    val responsBody = response.body()
                    if(responsBody != null){
                        setReveiewNameData(responsBody.items)
                    }
                }else{
                    Log.e(TAG,"onFilure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserAccountResponse>,t: Throwable){
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

//    private fun setUsernameAccountData(Username: ItemsItem){
//
//    }

    private fun setReveiewNameData(usernameData:List<ItemsItem>){
        val adapter = ListNameAdapter()
        adapter.submitList(usernameData)
        binding.rvUsername.adapter = adapter
//        binding.
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}