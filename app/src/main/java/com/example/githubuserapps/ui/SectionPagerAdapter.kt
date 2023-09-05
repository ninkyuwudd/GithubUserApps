package com.example.githubuserapps.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuserapps.fragment.FollowFragment
import com.example.githubuserapps.fragment.FollowingFragment

class SectionPagerAdapter(Activity: AppCompatActivity):FragmentStateAdapter(Activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}