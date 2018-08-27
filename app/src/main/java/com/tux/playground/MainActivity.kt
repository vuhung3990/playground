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
      sendLog("page1", "click button 1")
    }
    button2.setOnClickListener {
      sendLog("page1", "click button 2")
    }
  }

  /**
   * send log event with [page] is activity, [label] is event name or whatever you want
   */
  private fun sendLog(page: String, label: String) {
    Puree.send(ClickLog(page, label))
  }
}
