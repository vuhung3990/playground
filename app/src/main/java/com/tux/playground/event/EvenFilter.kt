package com.tux.playground.event

import android.util.Log
import com.cookpad.puree.PureeFilter
import com.google.gson.JsonObject

/**
 * only emit if [count] is even, this is an example to use filter
 */
class EvenFilter : PureeFilter {

  /**
   * increase on receive event
   * @see apply
   */
  private var count: Int = 0

  /**
   * increase counter and decide delivery event or not
   */
  override fun apply(jsonLog: JsonObject?): JsonObject? {
    count++
    Log.d("debug", "count: $count")
    return if (count % 2 == 0) jsonLog else null
  }
}