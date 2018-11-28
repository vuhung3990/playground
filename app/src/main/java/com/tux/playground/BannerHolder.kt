package com.tux.playground

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_banner.view.*

class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  fun bind(data: List<String>) {
    itemView.banner_viewpager.adapter = BannerAdapter(data)
  }
}