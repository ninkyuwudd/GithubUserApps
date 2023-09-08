package com.example.githubuserapps.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapps.data.response.UserFollowersResponseItem
import com.example.githubuserapps.databinding.ItemCardBinding
import com.example.githubuserapps.ui.ListNameAdapter.Companion.DIFF_CALLBACK
import com.squareup.picasso.Picasso

class FollowingAdapter:ListAdapter<UserFollowersResponseItem, FollowingAdapter.MyFollowingHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyFollowingHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyFollowingHolder(binding)
    }

    override fun onBindViewHolder(holder: MyFollowingHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class MyFollowingHolder(val binding:ItemCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(following: UserFollowersResponseItem){

            binding.itemText.text = following.login
            Picasso.get().load(following.avatarUrl).into(binding.itemImage)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserFollowersResponseItem>() {
            override fun areItemsTheSame(oldItem: UserFollowersResponseItem,newItem: UserFollowersResponseItem,): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserFollowersResponseItem,newItem: UserFollowersResponseItem,): Boolean {
                return oldItem == newItem
            }
        }
    }
}