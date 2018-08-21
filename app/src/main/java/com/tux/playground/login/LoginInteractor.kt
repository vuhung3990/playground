package com.tux.playground.login

class LoginInteractor(var output: LoginContract.InteractorOutput?) :
    LoginContract.Interactor {
  override fun login(mail: String, pwd: String) {
    if(mail.equals("sample@gmail.com") && pwd.equals("123456")) output?.onLoginSuccess()
    else output?.onLoginFail()
  }
}