package com.example.githubuserapps

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.databinding.ActivitySearchMenuBinding
import com.example.githubuserapps.factory.LovedViewModelFactory
import com.example.githubuserapps.settings.SettingViewModel
import com.example.githubuserapps.ui.ListNameAdapter
import com.example.githubuserapps.ui.MainViewModel
import com.example.githubuserapps.ui.insert.LovedAddUpdateViewModel
import com.example.githubuserapps.ui.page.LovedListActivity

class ActivitySearchMenu : AppCompatActivity() {

    private lateinit var binding:ActivitySearchMenuBinding
    private lateinit var listNameAdapter: ListNameAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsername.layoutManager = layoutManager

        val itemDecorator = DividerItemDecoration(this,layoutManager.orientation)
        binding.rvUsername.addItemDecoration(itemDecorator)
        listNameAdapter = ListNameAdapter()
        binding.rvUsername.adapter = listNameAdapter

        val fromMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)



        with(binding){
            svSearch.inflateMenu(R.menu.menut_top_option)
            val srcMenu = svSearch.menu
            val lovedMenuItem = srcMenu.findItem(R.id.item_love)
            val settingMenuItem = srcMenu.findItem(R.id.item_settings)
            val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK


            if(currentNightMode == Configuration.UI_MODE_NIGHT_YES ){
                lovedMenuItem.setIcon(R.drawable.ic_favorite_fill_white)
                settingMenuItem.setIcon(R.drawable.baseline_settings_24)
            }else{
                lovedMenuItem.setIcon(R.drawable.ic_favorite_fill)
                settingMenuItem.setIcon(R.drawable.ic_settings)
            }



            lovedMenuItem.setOnMenuItemClickListener {
                val itn = Intent(this@ActivitySearchMenu,LovedListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(itn)
                true
            }
            settingMenuItem.setOnMenuItemClickListener {
                val intent = Intent(this@ActivitySearchMenu, DarkModeSettingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                true
            }
            svSearchView.setupWithSearchBar(svSearch)
            svSearchView.editText.setOnClickListener{
                showLoading(true)
                svSearch.text = svSearchView.text

                svSearchView.hide()

                mainViewModel.findUsernameAccount(svSearchView.text.toString())
            }

        }


        mainViewModel.findUsernameAccount("gilang")

        mainViewModel.listReview.observe(this){
            username -> setReveiewNameData(username)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
        
    }

    private fun obtainViewModel(activity: AppCompatActivity): SettingViewModel {
        val factory = LovedViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(SettingViewModel::class.java)
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