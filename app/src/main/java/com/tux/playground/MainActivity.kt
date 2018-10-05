package com.tux.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button.setOnClickListener {
      val n1 = editText.text.toString().toInt()
      val n2 = editText2.text.toString().toInt()
      val result = n1 + n2
      textView2.text = result.toString()
    }
  }
}
