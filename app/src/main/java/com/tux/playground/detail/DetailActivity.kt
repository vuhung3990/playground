package com.tux.playground.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tux.playground.R

class DetailActivity : AppCompatActivity(), DetailContract.View {
  private lateinit var presenter: DetailPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_detail)

    presenter = DetailPresenter(this)
  }
}

