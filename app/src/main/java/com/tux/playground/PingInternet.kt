package com.tux.playground

import android.os.AsyncTask
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

/**
 * check internet connection
 */
class PingInternet(private val listener: PingResultReceiver) : AsyncTask<Int, Int, Boolean>() {

  /**
   * callback for ping result
   */
  interface PingResultReceiver {

    fun pingResult(result: Boolean)
  }

  override fun doInBackground(vararg param: Int?): Boolean {
    val timeout = param[0]
    return isInternetAvailable("8.8.8.8", 53, timeout ?: 1000)
  }

  override fun onPostExecute(result: Boolean?) {
    super.onPostExecute(result)
    listener.pingResult(result ?: false)
  }

  /**
   * check internet connection
   */
  private fun isInternetAvailable(address: String, port: Int, timeoutMs: Int): Boolean {
    return try {
      val sock = Socket()
      val sockAddress = InetSocketAddress(address, port)
      sock.connect(sockAddress, timeoutMs) // This will block no more than timeoutMs
      sock.close()

      true
    } catch (e: IOException) {
      e.printStackTrace()
      false
    }
  }
}