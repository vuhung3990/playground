package com.tux.playground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tux.playground.databinding.MyItemBinding

class SimpleAdapter : RecyclerView.Adapter<SimpleViewHolder>() {
  private val diffUtil = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem.contentEquals(newItem)
  }
  private val mDiffer = AsyncListDiffer(this, diffUtil)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
    val itemBinding = MyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return SimpleViewHolder(itemBinding)
  }

  override fun getItemCount(): Int = mDiffer.currentList.size

  override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
    val d = mDiffer.currentList[position]
    holder.bind(d)
  }

  fun replace(fakeData: List<String>) {
    mDiffer.submitList(fakeData)
  }
}