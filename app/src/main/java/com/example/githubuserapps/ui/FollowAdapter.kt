package com.example.githubuserapps.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.data.response.UserFollowersResponseItem
import com.example.githubuserapps.databinding.ItemCardBinding
import com.example.githubuserapps.ui.ListNameAdapter.Companion.DIFF_CALLBACK
import com.squareup.picasso.Picasso

class FollowAdapter:ListAdapter<UserFollowersResponseItem,FollowAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyViewHolder(val binding: ItemCardBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(review: UserFollowersResponseItem){
            binding.itemText.text = review.login
            Picasso.get().load(review.avatarUrl).into(binding.itemImage)
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