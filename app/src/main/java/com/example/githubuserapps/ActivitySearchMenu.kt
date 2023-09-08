package com.example.githubuserapps

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
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
//    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsername.layoutManager = layoutManager

        val itemDecorator = DividerItemDecoration(this,layoutManager.orientation)
        binding.rvUsername.addItemDecoration(itemDecorator)

        val fromMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding){
            svSearchView.setupWithSearchBar(svSearch)
            svSearchView.editText.setOnEditorActionListener {txtView,actionId,event ->
                svSearch.text = svSearchView.text

                svSearchView.hide()

                fromMainViewModel.findUsernameAccount(svSearchView.text.toString())
                Toast.makeText(this@ActivitySearchMenu, svSearchView.text, Toast.LENGTH_SHORT).show()

                false
            }
        }


        val mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)



        mainViewModel.listReview.observe(this){
            username -> setReveiewNameData(username)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
        
    }


    private fun setReveiewNameData(usernameData:List<ItemsItem>){
        val adapter =ListNameAdapter()
        adapter.submitList(usernameData)
        binding.rvUsername.adapter = adapter
//
    }



    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}