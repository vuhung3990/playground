package com.tux.playground.event

import com.cookpad.puree.PureeLog

/**
 * define event object
 */
data class ClickLog(val page: String, val label: String) : PureeLog
