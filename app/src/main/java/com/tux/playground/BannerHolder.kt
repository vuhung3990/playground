package com.tux.playground

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  fun bind(data: List<String>, lastBannerIndex: Int) {
    itemView.banner_viewpager.adapter = BannerAdapter(data)
    itemView.banner_viewpager.currentItem = lastBannerIndex
  }

  fun lastIndex() = itemView.banner_viewpager.currentItem
}