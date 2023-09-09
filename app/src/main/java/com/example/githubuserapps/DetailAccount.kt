package com.example.githubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.githubuserapps.data.response.DetailUserResponse
import com.example.githubuserapps.databinding.ActivityDetailAccountBinding
import com.example.githubuserapps.ui.DetailViewModel
import com.example.githubuserapps.ui.FollowViewModel
import com.example.githubuserapps.ui.MainViewModel
import com.example.githubuserapps.ui.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class DetailAccount: AppCompatActivity() {

    private lateinit var binding:ActivityDetailAccountBinding

    companion object {
        const val EXTRA_TITLE = "gizipp"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getName = intent.getStringExtra(EXTRA_TITLE)

        val bundle = Bundle()
        bundle.putString(EXTRA_TITLE,getName)

        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)

        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs,viewPager) {
            tab,position -> tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

        supportActionBar?.elevation = 0f



        val fromDetailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val detailViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java
        )

        fromDetailViewModel.findDetailUsernameAccount(getName.toString())

        detailViewModel.DetailDataAccount.observe(this){
            username -> setReviewNameData(username)
        }

        detailViewModel.isloading.observe(this){
            showLoading(it)
        }
    }

    private fun setReviewNameData(usernameData:DetailUserResponse){
        binding.titleDetail.text = usernameData.name
        binding.tvUsername.text = usernameData.login
        binding.companyValue.text = usernameData.company
        binding.locationValue.text = usernameData.location
        binding.gitsCount.text = usernameData.publicGists.toString()
        binding.reposCount.text = usernameData.publicRepos.toString()
        binding.followersCount.text = usernameData.followers.toString()
        binding.followingCount.text = usernameData.following.toString()
        Picasso.get().load(usernameData.avatarUrl).into(binding.profileImage)

    }


    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}