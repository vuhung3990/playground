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

  /**
   * event type
   */
  private val TYPE = "out_logcat"

  /**
   * configure output such as: [OutputConfiguration.maxRetryCount], [OutputConfiguration.flushIntervalMillis], [OutputConfiguration.logsPerRequest]
   */
  override fun configure(conf: OutputConfiguration): OutputConfiguration {
    return conf.apply {
      maxRetryCount = 3
      flushIntervalMillis = 1000
      logsPerRequest = 5
    }
  }

  /**
   * delivery event with [JsonObject], print log and save into database
   */
  override fun emit(jsonLog: JsonObject) {
    Log.d(TYPE, jsonLog.toString())
    PureeSQLiteStorage(context).insert(TYPE, jsonLog)
  }

  /**
   * @see TYPE
   */
  override fun type(): String {
    return TYPE
  }
}
