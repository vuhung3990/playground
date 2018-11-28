package com.tux.playground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

const val BANNER_POSITION = 0
const val VIEWTYPE_BANNER = 0
const val VIEWTYPE_ITEM = 1

class SimpleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  /**
   * list for item
   */
  private var listData = listOf<String>()
  /**
   * list for displaying banner on top
   */
  private var bannerList = listOf<String>()
  private var lastBannerIndex = 0

  fun updateList(listUpdate: List<String>) {
    listData = listUpdate
    notifyDataSetChanged()
  }

  fun setBannerList(listUpdate: List<String>) {
    bannerList = listUpdate
    notifyItemChanged(BANNER_POSITION)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when (viewType) {
      VIEWTYPE_BANNER -> {
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
    return if (position == BANNER_POSITION) VIEWTYPE_BANNER else VIEWTYPE_ITEM
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val viewType = getItemViewType(position)
    val data = listData[position]
    when (viewType) {
      VIEWTYPE_BANNER -> {
        (holder as BannerHolder).bind(bannerList, lastBannerIndex)
      }
      else -> {
        (holder as SimpleHolder).bind(data)
      }
    }
  }

  override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
    if (holder is BannerHolder) {
      lastBannerIndex = holder.lastIndex()
    }
    super.onViewRecycled(holder)
  }
}