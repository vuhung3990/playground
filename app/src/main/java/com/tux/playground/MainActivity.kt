package com.tux.playground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  val CHANNEL_ID = "chanel_81"
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    tx.setOnClickListener {
      val pIntent = TaskStackBuilder.create(this)
          .addNextIntentWithParentStack(Intent(this, MainActivity::class.java))
          .addNextIntent(Intent(this, Main2Activity::class.java))
          .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

      createNotificationChannel()
      val noti = NotificationCompat.Builder(this, CHANNEL_ID)
          .setSmallIcon(R.mipmap.ic_launcher)
          .setContentTitle("title")
          .setContentText("content text")
          .setContentIntent(pIntent)
          .setPriority(NotificationCompat.PRIORITY_DEFAULT)
          .build()

      NotificationManagerCompat.from(this).notify(88, noti)
    }
  }

  private fun createNotificationChannel() {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val name = "chanel name"
      val descriptionText = "chanel des"
      val importance = NotificationManager.IMPORTANCE_DEFAULT
      val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
        description = descriptionText
      }
      // Register the channel with the system
      val notificationManager: NotificationManager =
          getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      notificationManager.createNotificationChannel(channel)
    }
  }
}
