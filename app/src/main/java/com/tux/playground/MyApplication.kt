package com.tux.playground

import android.app.Application
import android.content.Context
import com.cookpad.puree.Puree
import com.cookpad.puree.PureeConfiguration
import com.tux.playground.event.AddEventTimeFilter
import com.tux.playground.event.ClickLog
import com.tux.playground.event.OutLogcat
import java.util.concurrent.Executors


class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    val addEventTimeFilter = AddEventTimeFilter()
    Puree.initialize(PureeConfiguration.Builder(this)
        .executor(Executors.newScheduledThreadPool(1)) // optional
        .register(ClickLog::class.java, OutLogcat(this))
        .build())
  }
}