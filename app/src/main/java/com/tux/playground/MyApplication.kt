package com.tux.playground

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class MyApplication : Application(), KodeinAware {
  override val kodein = Kodein.lazy {
    /* bindings */
  }
}