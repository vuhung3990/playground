package com.tux.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val alert = AlertDialogFragment.newInstance("message", "title", positive = "ok")
    alert.show(supportFragmentManager, "alert")
  }
}
