package com.example.githubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso

class DetailAccount: AppCompatActivity(), View.OnClickListener {

    private var isEdit = false
    private var loved: Loved? = null

    private lateinit var lovedAddUpdateViewModel: LovedAddUpdateViewModel


    private lateinit var binding:ActivityDetailAccountBinding
    private lateinit var backbtn : ImageView

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20

        const val EXTRA_TITLE = "gizipp"
        const val EXTRA_IMG_ACCOUNT = "extra_img"

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

        loved = intent.getParcelableExtra(EXTRA_NOTE)
        if (loved != null) {
            isEdit = true
        } else {
            loved = Loved()
        }

        val actionBarTitle: String
        val btnTitle: String
//
//
//        if (isEdit) {
//            actionBarTitle = getString(R.string.change)
//            btnTitle = getString(R.string.update)
//            if (note != null) {
//                note?.let { note ->
//                    binding?.edtTitle?.setText(note.title)
//                    binding?.edtDescription?.setText(note.description)
//                }
//            }
//        } else {
//            actionBarTitle = getString(R.string.add)
//            btnTitle = getString(R.string.save)
//        }


        binding.floatingLove.setOnClickListener{
            val name = getName
            val urlImg = getUrlImg


                    loved.let { love ->
                        love!!.account_username = name.toString()
                        love.avatarImgUrl = urlImg
                    }
                    if (isEdit) {
                        lovedAddUpdateViewModel.update(loved as Loved)
                        showToast("edited")
                    } else {
//                        loved.let { love ->
//                            love?. = DateHelper.getCurrentDate()
//                        }
                        lovedAddUpdateViewModel.insert(loved as Loved)
                        showToast("New Loved Account")
                    }
                    finish()

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