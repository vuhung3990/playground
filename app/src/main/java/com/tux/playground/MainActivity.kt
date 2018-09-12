package com.tux.playground

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.telephony.*
import android.telephony.TelephonyManager.PHONE_TYPE_GSM
import android.util.Log


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Log.d("debug", "${getNetworkType(this)}")
    Log.d("debug", getOsVersion())
    Log.d("debug", getBatteryLevel(this))



    Log.d("debug", "$linkSpeed  $linkSpeed2")
  }
}
