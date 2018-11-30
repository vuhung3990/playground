package com.tux.playground

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val ss = "Android is a Software stack http://wakulive.com ruin my life google.com fuck up my night https://www.youtube.com"

    text.movementMethod = LinkMovementMethod.getInstance()
    text.text = ss.spannableLink()
    // android:text="http://google.com ruin my life"
  }
}

class MyClickableSpan(val text: String) : ClickableSpan() {
  override fun onClick(view: View) {
    Log.d("aaaa", "click on $text")
  }
}

/**
 * create spannable url link
 */
fun String.spannableLink(): SpannableString {
  val listWebUrlRange = this.detectWebUrl()
  val ss = SpannableString(this)

  for (range in listWebUrlRange) {
    val clickable = MyClickableSpan(this.substring(range))
    ss.setSpan(clickable, range.start, range.last + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
  }

  return ss
}

/**
 * return list range of web url inside input
 */
private fun String.detectWebUrl(): List<IntRange> {
  val regex = PatternsCompat.AUTOLINK_WEB_URL.toRegex()
  return regex.findAll(this)
      .map { it.range }
      .toList()
}