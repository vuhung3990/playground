package com.tux.playground

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class BannerAdapter(private val listData: List<String>) : PagerAdapter() {
  // This method should create the page for the given position passed to it as an argument.
  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    // create image for view pager
    val image = ImageView(container.context)
    image.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    // load image from url
    Glide.with(image)
        .load(listData[position])
        .into(image)

    // Add the page to the container
    container.addView(image)
    return image
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }

  override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

  override fun getCount(): Int = listData.size
}