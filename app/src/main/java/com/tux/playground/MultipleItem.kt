package com.tux.playground

import com.chad.library.adapter.base.entity.MultiItemEntity

const val VIEW_TYPE_1 = 1
const val VIEW_TYPE_2 = 2

class MultipleItem(var viewType: Int = 1, val text: String) : MultiItemEntity {
  override fun getItemType(): Int = viewType
}