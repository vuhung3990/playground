package com.tux.playground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import me.leolin.shortcutbadger.ShortcutBadger

private const val NOTIFICATION_CHANNEL: String = "my.channel"

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    Log.d("debug", "isSupported: ${ShortcutBadger.isBadgeCounterSupported(this)}")
    Log.d("debug", "isXiaomiDevice = ${isXiaomiDevice(Build.MANUFACTURER)}")

    showBadge(44)
  }

  private fun showBadge(count: Int) {
    if (isXiaomiDevice(Build.MANUFACTURER)) {
      val notificationManager = getSystemService(
          Context.NOTIFICATION_SERVICE) as NotificationManager
      createChannel(notificationManager, NOTIFICATION_CHANNEL)
      val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
          .setSmallIcon(R.mipmap.ic_launcher_round)
          .build()

      ShortcutBadger.applyNotification(applicationContext, notification, count)
      notificationManager.notify(22, notification)
    } else {
      ShortcutBadger.applyCount(applicationContext, count)
    }
  }

  private fun createChannel(notificationManager: NotificationManager,
      NOTIFICATION_CHANNEL: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(NOTIFICATION_CHANNEL, "ShortcutBadger Sample",
          NotificationManager.IMPORTANCE_DEFAULT)
      notificationManager.createNotificationChannel(channel)
    }
  }

  private fun isXiaomiDevice(manufacturer: String): Boolean {
    return manufacturer.equals("xiaomi", true)
  }
}
