package com.example.githubuserapps

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.data.response.UserAccountResponse
import com.example.githubuserapps.data.retrofit.ApiConfig
import com.example.githubuserapps.databinding.ActivitySearchMenuBinding
import com.example.githubuserapps.ui.ListNameAdapter
import com.example.githubuserapps.ui.MainViewModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class ActivitySearchMenu : AppCompatActivity() {

    private lateinit var binding:ActivitySearchMenuBinding
//
//    companion object {
//        private const val USER = "Username"
//        private const val USERNAME_ACCOUNT = "sidiqpermana"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            svSearchView.editText.setOnEditorActionListener {txtView,actionId,event ->
                svSearch.text = svSearchView.text
                svSearchView.setupWithSearchBar(svSearch)
                svSearchView.hide()
//                val bundle = Bundle()
//                bundle.putString(MainViewModel.USERNAME_ACCOUNT, svSearchView.text.toString())
                Toast.makeText(this@ActivitySearchMenu, svSearchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }

        val mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)
        mainViewModel.listReview.observe(this){
            username -> setReveiewNameData(username)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsername.layoutManager = layoutManager
        val itemDecorator = DividerItemDecoration(this,layoutManager.orientation)
        binding.rvUsername.addItemDecoration(itemDecorator)


        mainViewModel.listReview.observe(this){
            usernameList -> setReveiewNameData(usernameList)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
        
    }


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