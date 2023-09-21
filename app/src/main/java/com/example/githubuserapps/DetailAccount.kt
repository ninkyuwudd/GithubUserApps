package com.example.githubuserapps

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.githubuserapps.data.response.DetailUserResponse
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.databinding.ActivityDetailAccountBinding
import com.example.githubuserapps.factory.LovedViewModelFactory
import com.example.githubuserapps.ui.DetailViewModel
import com.example.githubuserapps.ui.FollowViewModel
import com.example.githubuserapps.ui.MainViewModel
import com.example.githubuserapps.ui.SectionPagerAdapter
import com.example.githubuserapps.ui.insert.LovedAddUpdateViewModel
import com.example.githubuserapps.ui.page.LovedViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class DetailAccount: AppCompatActivity(), View.OnClickListener {



    private var isEdit = false
    private var loved: Loved? = null

    private lateinit var lovedAddUpdateViewModel: LovedAddUpdateViewModel

    private lateinit var binding:ActivityDetailAccountBinding


    companion object {
        const val EXTRA_LOVED = "extra_loved"

        const val EXTRA_TITLE = "gizipp"
        const val EXTRA_IMG_ACCOUNT = "extra_img"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var txt: TextView
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getName = intent.getStringExtra(EXTRA_TITLE)
        val getUrlImg = intent.getStringExtra(EXTRA_IMG_ACCOUNT)

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

        binding.bckBtn.setOnClickListener(this)



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

        lovedAddUpdateViewModel = obtainViewModel(this@DetailAccount)


        loved = intent.getParcelableExtra(EXTRA_LOVED)

        if (loved != null) {
            isEdit = true
        } else {
            loved = Loved()
        }

        lovedAddUpdateViewModel.getAllLovedByName(getName.toString()).observe(this, Observer {
            lovedData ->
            if(lovedData != null){
                isEdit = true
                loved = Loved(lovedData.id,lovedData.account_username,lovedData.avatarImgUrl)
                Log.d("test flag 1","Loged data != null cuy")
                binding.floatingLove.setImageResource(R.drawable.ic_favorite_fill)
            }else{
                loved = Loved()
                Log.d("test flag 2","Loged data null cuy")
                binding.floatingLove.setImageResource(R.drawable.ic_favorite_border)
            }
        })


        binding.floatingLove.setOnClickListener{

                    loved.let { love ->
                        love!!.account_username = getName.toString()
                        love.avatarImgUrl = getUrlImg
                    }
                    if (isEdit) {
                        lovedAddUpdateViewModel.delete(loved as Loved)
                        showToast("Deleted loved account!")
                        Log.d("test flag 1","mencoba hapus data cuy")
                        binding.floatingLove.setImageResource(R.drawable.ic_favorite_border)
                    } else {
                        lovedAddUpdateViewModel.insert(loved as Loved)
                        showToast("New Loved Account")
                        binding.floatingLove.setImageResource(R.drawable.ic_favorite_fill)
                    }
                    isEdit = true
//                    finish()

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): LovedAddUpdateViewModel {
        val factory = LovedViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LovedAddUpdateViewModel::class.java)
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bck_btn -> {
                onBackPressed()
            }
        }
    }


}