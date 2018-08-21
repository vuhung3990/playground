package com.tux.playground.login

import android.support.v4.util.PatternsCompat

fun String.isValidEmail(): Boolean {
  return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}