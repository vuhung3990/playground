package com.tux.playground

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ProgressFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view: View = inflater.inflate(R.layout.loading, container, false)
    val loadingImage: ImageView = view.findViewById(R.id.loading_img)
    (loadingImage.drawable as AnimationDrawable?)?.start()
    return view
  }
}