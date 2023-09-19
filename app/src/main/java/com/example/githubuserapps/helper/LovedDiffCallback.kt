package com.example.githubuserapps.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubuserapps.database.Loved

class LovedDiffCallback(private val oldLovedList: List<Loved>, private val newLovedList: List<Loved>):DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldLovedList.size
    override fun getNewListSize(): Int = newLovedList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldLovedList[oldItemPosition].id == newLovedList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldLoved = oldLovedList[oldItemPosition]
        val newLoved = newLovedList[newItemPosition]
        return oldLoved.account_username == newLoved.account_username && oldLoved.avatarImgUrl == newLoved.avatarImgUrl
    }
}