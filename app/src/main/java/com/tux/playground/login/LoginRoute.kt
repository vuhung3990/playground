package com.tux.playground.login

import android.app.Activity
import android.content.Intent
import com.tux.playground.detail.DetailActivity

class LoginRoute(val activity: Activity) : LoginContract.Route {
  override fun gotoDetail() {
    activity.startActivity(Intent(activity, DetailActivity::class.java))
  }
}
