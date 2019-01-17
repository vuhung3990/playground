package com.tux.playground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tux.playground.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
  private val user: User by inject()
  private val mainVM by inject<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.vm = mainVM
    binding.setLifecycleOwner(this)

    Log.d("vm", mainVM.user.toString())
    Log.d("koin", user.toString())
  }
}
