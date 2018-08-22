package com.tux.playground.event

import android.content.Context
import android.util.Log
import com.cookpad.puree.outputs.OutputConfiguration
import com.cookpad.puree.outputs.PureeOutput
import com.cookpad.puree.storage.PureeSQLiteStorage
import com.google.gson.JsonObject

/**
 * when receive event: save to db, send log to server or just print log
 * @see OutLogcat.emit
 */
class OutLogcat(val context: Context) : PureeOutput() {

  private val TYPE = "out_logcat"

  override fun configure(conf: OutputConfiguration): OutputConfiguration {
    return conf.apply {
      maxRetryCount = 3
      flushIntervalMillis = 1000
      logsPerRequest = 5
    }
  }

  override fun emit(jsonLog: JsonObject) {
    Log.d(TYPE, jsonLog.toString())
    PureeSQLiteStorage(context).insert(TYPE, jsonLog)
  }

  override fun type(): String {
    return TYPE
  }
}
