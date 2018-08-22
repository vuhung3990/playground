package com.tux.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cookpad.puree.Puree
import com.tux.playground.event.ClickLog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    button1.setOnClickListener {
      Puree.send(ClickLog("page1", "click button 1"))
    }
    button2.setOnClickListener {
      Puree.send(ClickLog("page1", "click button 2"))
    }
  }
}
