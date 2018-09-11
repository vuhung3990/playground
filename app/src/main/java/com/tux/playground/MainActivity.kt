package com.tux.playground

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private val PREF_NAME: String = "file_pref"
  private val KEY: String = "kk"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    startActivity(Intent().setClassName("com.evenwell.powersaving.g3","com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity"))
  }
}
