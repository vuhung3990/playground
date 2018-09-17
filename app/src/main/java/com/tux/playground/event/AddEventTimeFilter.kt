package com.tux.playground.event

import android.content.Context
import android.os.Build
import com.cookpad.puree.PureeFilter
import com.google.gson.JsonObject

/**
 * add time stamp on each event
 */
class AddEventTimeFilter(private val context: Context) : PureeFilter {

  override fun apply(jsonLog: JsonObject?): JsonObject? {
    jsonLog?.addProperty("os_version", Build.VERSION.SDK_INT)
    jsonLog?.addProperty("package", context.packageName)
    jsonLog?.addProperty("event_time", System.currentTimeMillis())
    return jsonLog
  }

}
