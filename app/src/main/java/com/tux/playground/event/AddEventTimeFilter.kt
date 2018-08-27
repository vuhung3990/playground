package com.tux.playground.event

import com.cookpad.puree.PureeFilter
import com.google.gson.JsonObject

/**
 * add time stamp on each event
 */
class AddEventTimeFilter : PureeFilter {

  override fun apply(jsonLog: JsonObject?): JsonObject? {
    jsonLog?.addProperty("event_time", System.currentTimeMillis())
    return jsonLog
  }

}
