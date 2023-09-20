package com.example.githubuserapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.databinding.ActivitySearchMenuBinding
import com.example.githubuserapps.ui.ListNameAdapter
import com.example.githubuserapps.ui.MainViewModel
import com.example.githubuserapps.ui.page.LovedListActivity

class ActivitySearchMenu : AppCompatActivity() {

    private lateinit var binding:ActivitySearchMenuBinding
    private lateinit var listNameAdapter: ListNameAdapter
//    private lateinit var mainViewModel: MainViewModel

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

//    private fun showPopup(view: MenuItem){
//        when (view.itemId) {
//            R.id.item_settings -> {
//                val popupMenu = PopupMenu(this, findViewById(R.id.menu_darkmode))
//                popupMenu.inflate(R.menu.setting_menu)
//                popupMenu.setOnMenuItemClickListener { itemMenu ->
//                    when (itemMenu.itemId) {
//                        R.id.menu_darkmode -> {
//                            val intent = Intent(this@ActivitySearchMenu, DarkModeSettingActivity::class.java)
//                            startActivity(intent)
//                            true
//                        }
//                        else -> false
//                    }
//                }
//                popupMenu.show()
//            }
//        }


//    }

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