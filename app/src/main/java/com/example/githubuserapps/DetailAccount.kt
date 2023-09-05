package com.example.githubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.githubuserapps.ui.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailAccount : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "put_extra_title"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_account)

        val getName = intent.getStringExtra(EXTRA_TITLE)

        txt = findViewById(R.id.titleDetail)
        txt.text = getName


        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs,viewPager) {
            tab,position -> tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}