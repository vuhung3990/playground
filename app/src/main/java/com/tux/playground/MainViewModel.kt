package com.tux.playground

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
  val text: MutableLiveData<String> by lazy {
    MutableLiveData<String>()
  }

  fun onClickButton1() {
    Log.d("aa", "button 1")
    text.value = "button 1 click"
  }

  fun onClickButton2() {
    Log.d("aa", "button 2")
    text.value = "button 2 click"
  }

  fun onClickButton3() {
    Log.d("aa", "button 3")
    text.value = "button 3 click"
  }
}