package com.example.githubuserapps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapps.DetailAccount
import com.example.githubuserapps.R
import com.example.githubuserapps.data.response.UserFollowersResponseItem
import com.example.githubuserapps.databinding.FragmentFollowBinding
import com.example.githubuserapps.databinding.FragmentFollowingBinding
import com.example.githubuserapps.ui.FollowAdapter
import com.example.githubuserapps.ui.FollowViewModel
import com.example.githubuserapps.ui.FollowingAdapter
import com.example.githubuserapps.ui.FollowingViewModel

class FollowingFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingAdapter: FollowingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments
        val usrLogin:String = data?.getString(DetailAccount.EXTRA_TITLE).toString()

        recyclerView = binding.rvFollowing
        followingAdapter = FollowingAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = followingAdapter

        val itemDecorator = DividerItemDecoration(context,LinearLayoutManager(context).orientation)
        recyclerView.addItemDecoration(itemDecorator)


        val followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)

        followingViewModel.fetchUsernameFollowingData(usrLogin)

        followingViewModel.listFollowingUserData.observe(viewLifecycleOwner){
                username -> setFollowingNameData(username)
        }


        followingViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }

    private fun setFollowingNameData(usernameData: List<UserFollowersResponseItem>){
        val adapter = FollowAdapter()
        adapter.submitList(usernameData)
        binding.rvFollowing.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarFollowing.visibility = View.VISIBLE
        } else {
            binding.progressBarFollowing.visibility = View.GONE
        }
    }



}