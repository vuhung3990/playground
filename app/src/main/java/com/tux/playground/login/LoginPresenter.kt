package com.tux.playground.login

import android.app.Activity
import android.text.TextUtils

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter,
    LoginContract.InteractorOutput {

  override fun onLoginFail() {
    view.showLoginFail()
  }

  override fun onLoginSuccess() {
    route.gotoDetail()
  }

  private val interactor = LoginInteractor(this)
  private val route = LoginRoute(view as Activity)

  override fun loginButtonPressed(mail: String, pwd: String) {
    if (TextUtils.isEmpty(mail)) view.showEmailBlank()
    else if (TextUtils.isEmpty(pwd)) view.showPasswordBlank()
    else if (!mail.isValidEmail()) view.showInvalidEmailFormat()
    else {
      interactor.login(mail, pwd)
    }
  }

}
