package com.tux.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vdurmont.emoji.EmojiParser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val a = "\uD83D\uDC6F\u200D♂️️"
    val b = EmojiParser.extractEmojis(text.text.toString()).size
    println(b)
  }
}
