package com.tux.playground

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MultipleItemQuickAdapter :
    BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder>(mutableListOf<MultipleItem>()) {

  init {
    addItemType(VIEW_TYPE_1, R.layout.item_normal)
    addItemType(VIEW_TYPE_2, R.layout.item_extra)
  }

  private var currentSelected = -1

  override fun convert(helper: BaseViewHolder?, item: MultipleItem?) {
    helper?.setText(R.id.text, item?.text)
  }

  fun itemClick(position: Int) {
    if (currentSelected >= 0) {
      val currentItem = getItem(currentSelected)
      currentItem?.viewType = VIEW_TYPE_1
      notifyItemChanged(currentSelected)
    }

    val item = getItem(position)
    item?.viewType = VIEW_TYPE_2
    notifyItemChanged(position)

    currentSelected = position
  }
}