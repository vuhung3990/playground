package com.tux.playground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SimpleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private var listData = listOf<String>()
  private var bannerList = listOf<String>()

  fun updateList(listUpdate: List<String>) {
    listData = listUpdate
    notifyDataSetChanged()
  }

  fun setBannerList(listUpdate: List<String>) {
    bannerList = listUpdate
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      0 -> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        BannerHolder(view)
      }
      else -> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_simple, parent, false)
        SimpleHolder(view)
      }
    }
  }

  override fun getItemCount(): Int = listData.size

  override fun getItemViewType(position: Int): Int {
    // fist item default = 0 (Banner type)
    return if (position == 0) 0 else 1
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val viewType = getItemViewType(position)
    val data = listData[position]
    when (viewType) {
      0 -> {
        (holder as BannerHolder).bind(bannerList)
      }
      else -> {
        (holder as SimpleHolder).bind(data)
      }
    }
  }
}