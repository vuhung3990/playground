package com.tux.playground

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.BreakIterator
import java.text.Normalizer


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

  fun length(s: String): Int {
    var n = 0
    var i = 0
    while (i < s.length) {
      val cp = s.codePointAt(i)
      i += Character.charCount(cp)
      ++n
    }
    return n
  }

  fun dump(s: String) {
    var n = 0
    var i = 0
    while (i < s.length) {
      val cp = s.codePointAt(i)
      val bytes = Character.charCount(cp)
      i += bytes
      System.out.printf("[%d] #%dB: U+%X = %s%n",
          n, bytes, cp, Character.getName(cp))
      ++n
    }
    System.out.printf("Length:%d%n", n)
  }

  fun printLength(s: String) {
    val it = BreakIterator.getCharacterInstance()
    it.setText(s)
    var count = 0
    while (it.next() != BreakIterator.DONE) {
      count++
    }
    println("Grapheme length: $count $s")
  }

  @Test
  fun addition_isCorrect() {
    val emo = 0x1F468

    println(emo)
    val result: Int = 1
    assertEquals(1, result)
  }
}
