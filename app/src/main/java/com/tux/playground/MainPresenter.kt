package com.tux.playground

import android.widget.Toast

class MainPresenter(val view: MainActivity) {
  fun onClickText() {
    Toast.makeText(view, "show toast", Toast.LENGTH_LONG).show()
  }
}