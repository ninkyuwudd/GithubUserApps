package com.example.githubuserapps.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapps.ActivitySearchMenu
import com.example.githubuserapps.DetailAccount
import com.example.githubuserapps.data.response.ItemsItem
import com.example.githubuserapps.databinding.ItemCardBinding
import com.example.githubuserapps.fragment.FollowFragment
import com.squareup.picasso.Picasso

class ListNameAdapter : ListAdapter<ItemsItem,ListNameAdapter.MyViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    class MyViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: ItemsItem){

            binding.itemText.text = "${review.login}"
            Picasso.get().load(review.avatarUrl).into(binding.itemImage)

            binding.itemCard.setOnClickListener{
                val ctx = binding.root.context

                val intent = Intent(ctx,DetailAccount::class.java)
                intent.putExtra(DetailAccount.EXTRA_TITLE,review.login)
                ctx.startActivity(intent)

            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}