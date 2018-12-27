package com.tux.playground

import androidx.recyclerview.widget.RecyclerView
import com.tux.playground.databinding.MyItemBinding

class SimpleViewHolder(private val itemBinding: MyItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
  fun bind(city: String) {
    itemBinding.city = city
  }
}
