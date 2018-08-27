package com.tux.playground.event

import com.cookpad.puree.PureeLog

/**
 * define event object, A log class is required to implement [PureeLog], which is a marker interface just like as [Serializable], to serialize logs with Gson
 */
data class ClickLog(val page: String, val label: String) : PureeLog
