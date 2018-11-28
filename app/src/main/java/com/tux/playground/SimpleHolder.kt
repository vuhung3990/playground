package com.tux.playground

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_simple.view.*

class SimpleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  fun bind(data: String) {
    itemView.item_text.text = data
  }
}