package com.tux.playground.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tux.playground.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
  override fun showLoginFail() {
    Toast.makeText(this, "login fail cause email or password incorrect.", Toast.LENGTH_SHORT).show()
  }

  private lateinit var presenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    presenter = LoginPresenter(this)
    login.setOnClickListener {
      val mail = email.text.toString()
      val pwd = password.text.toString()
      presenter.loginButtonPressed(mail, pwd)
    }
  }

  override fun showEmailBlank() {
    Toast.makeText(this, "email blank", Toast.LENGTH_SHORT).show()
  }

  override fun showPasswordBlank() {
    Toast.makeText(this, "password blank", Toast.LENGTH_SHORT).show()
  }

  override fun showInvalidEmailFormat() {
    Toast.makeText(this, "invalid email format", Toast.LENGTH_SHORT).show()
  }
}
