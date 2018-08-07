package com.tux.playground

import android.os.AsyncTask
import android.util.Log

class CountAsync(val name: String) : AsyncTask<Int, Int, Boolean>() {
  override fun doInBackground(vararg param: Int?): Boolean {
    val count = param[0]
    count?.let {
      for (i in 0..count) {
        Thread.sleep(100)
        Log.d(name, "count = $i")
      }
    }
    return true
  }
}