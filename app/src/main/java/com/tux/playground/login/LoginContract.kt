package com.tux.playground.login

interface LoginContract {
  interface View {
    fun showEmailBlank()
    fun showPasswordBlank()
    fun showInvalidEmailFormat()
    fun showLoginFail()
  }

  interface Interactor {
    fun login(mail: String, pwd: String)
  }

  interface InteractorOutput {
    fun onLoginFail()
    fun onLoginSuccess()
  }

  interface Presenter {
    fun loginButtonPressed(mail: String, pwd: String)
  }

  interface Route {
    fun gotoDetail()
  }
}