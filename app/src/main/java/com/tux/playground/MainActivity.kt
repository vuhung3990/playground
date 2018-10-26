package com.tux.playground

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tux.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private var binding: ActivityMainBinding? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    binding?.apply {
      user = User("peter tun")
      presenter = MainPresenter(this@MainActivity)
    }
  }
}
