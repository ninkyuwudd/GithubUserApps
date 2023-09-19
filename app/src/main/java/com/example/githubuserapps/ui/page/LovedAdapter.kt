package com.example.githubuserapps.ui.page

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserapps.DetailAccount
import com.example.githubuserapps.database.Loved
import com.example.githubuserapps.databinding.ItemCardBinding
import com.example.githubuserapps.helper.LovedDiffCallback
import com.squareup.picasso.Picasso

class LovedAdapter: RecyclerView.Adapter<LovedAdapter.LovedViewHolder>() {


    private val listLoved = ArrayList<Loved>()
    fun setListLoved(listNotes: List<Loved>) {
        val diffCallback = LovedDiffCallback(this.listLoved, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listLoved.clear()
        this.listLoved.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LovedViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LovedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLoved.size
    }

    override fun onBindViewHolder(holder: LovedViewHolder, position: Int) {
        holder.bind(listLoved[position])
    }
    class LovedViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loved: Loved) {
            val ctx = binding.root.context
            with(binding) {
                itemText.text = loved.account_username
                Picasso.get().load(loved.avatarImgUrl).into(binding.itemImage)
                itemCard.setOnClickListener {
                    val intent = Intent(ctx, DetailAccount::class.java)
                    intent.putExtra(DetailAccount.EXTRA_TITLE,loved.account_username)
                    intent.putExtra(DetailAccount.EXTRA_IMG_ACCOUNT,loved.avatarImgUrl)
                    intent.putExtra(DetailAccount.EXTRA_NOTE,loved)
                    ctx.startActivity(intent)
                }
            }
        }
    }
}