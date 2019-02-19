package com.tux.playground

import android.os.AsyncTask
import android.util.Log
import org.jsoup.Jsoup


class GetWebTitle : AsyncTask<String, Int, String>() {
  override fun doInBackground(vararg params: String?): String {
    val url = params[0]
    val doc = Jsoup.connect(url).get()
    val webTitle = doc.selectFirst("div.wv_title").text()
    return if (webTitle.isNullOrBlank()) {
      val title = doc.selectFirst("title").text()
      title
    } else
      webTitle
  }

  override fun onPostExecute(result: String?) {
    super.onPostExecute(result)
    Log.d("aaaaa", result)
  }
}