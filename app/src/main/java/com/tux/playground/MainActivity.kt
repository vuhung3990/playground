package com.tux.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // detail here: https://stackoverflow.com/questions/29937556/asynctask-execute-or-executeonexecutor
    // sequence: if android honey comb+
//    CountAsync("async1").execute(30)
//    CountAsync("async2").execute(30)
//    CountAsync("async3").execute(30)
//    CountAsync("async4").execute(30)

    val executor = Executors.newSingleThreadExecutor()

    // multiple
    CountAsync("async5").executeOnExecutor(executor, 30)
    CountAsync("async6").executeOnExecutor(executor, 30)
  }
}
