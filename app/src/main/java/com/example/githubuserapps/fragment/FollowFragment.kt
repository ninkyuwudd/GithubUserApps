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
import com.example.githubuserapps.R
import com.example.githubuserapps.data.response.UserFollowersResponse
import com.example.githubuserapps.data.response.UserFollowersResponseItem
import com.example.githubuserapps.databinding.FragmentFollowBinding
import com.example.githubuserapps.ui.FollowAdapter
import com.example.githubuserapps.ui.FollowViewModel


class FollowFragment() : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentFollowBinding
    private lateinit var viewModel: FollowViewModel
    private lateinit var followersAdapter: FollowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)


        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvFollow
        followersAdapter = FollowAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = followersAdapter

        val itemDecorator = DividerItemDecoration(context,LinearLayoutManager(context).orientation)
        recyclerView.addItemDecoration(itemDecorator)

        val data = arguments?.getString("username")


        val followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowViewModel::class.java)

//        followersViewModel.setUsernameDataGet(data.toString())


        followersViewModel.listFollow.observe(viewLifecycleOwner){
                username -> setReveiewNameData(username)
        }


        followersViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }


    }

    private fun setReveiewNameData(usernameData: List<UserFollowersResponseItem>){
        val adapter = FollowAdapter()
        adapter.submitList(usernameData)
        binding.rvFollow.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}