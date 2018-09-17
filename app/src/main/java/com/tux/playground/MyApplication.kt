package com.tux.playground

import android.app.Application
import com.cookpad.puree.Puree
import com.cookpad.puree.PureeConfiguration
import com.tux.playground.event.AddEventTimeFilter
import com.tux.playground.event.ClickLog
import com.tux.playground.event.EvenFilter
import com.tux.playground.event.OutLogcat
import java.util.concurrent.Executors


class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()

    initPuree()
  }

  /**
   * Initial and configure Puree with [PureeConfiguration] in [Application.onCreate], which registers pairs of what and where.
   */
  private fun initPuree() {
    val addEventTimeFilter = AddEventTimeFilter(this)
    val evenFilter = EvenFilter()
    Puree.initialize(PureeConfiguration.Builder(this)
        .executor(Executors.newScheduledThreadPool(1)) // optional
        .register(ClickLog::class.java, OutLogcat(this).withFilters(addEventTimeFilter, evenFilter))
        .build())
  }
}