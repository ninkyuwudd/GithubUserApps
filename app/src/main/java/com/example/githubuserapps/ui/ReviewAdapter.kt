package com.example.githubuserapps.ui

import androidx.recyclerview.widget.ListAdapter

//class ReviewAdapter : ListAdapter<CustomerReviewsItem, ReviewAdapter.MyViewHolder>(DIFF_CALLBACK) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val review = getItem(position)
//        holder.bind(review)
//    }
//    class MyViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(review: CustomerReviewsItem){
//            binding.tvItem.text = "${review.review}\n- ${review.name}"
//        }
//    }
//    companion object {
//        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CustomerReviewsItem>() {
//            override fun areItemsTheSame(oldItem: CustomerReviewsItem, newItem: CustomerReviewsItem): Boolean {
//                return oldItem == newItem
//            }
//            override fun areContentsTheSame(oldItem: CustomerReviewsItem, newItem: CustomerReviewsItem): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//}