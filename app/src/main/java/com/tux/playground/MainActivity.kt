package com.tux.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.tux.playground.database.DatabaseApp
import com.tux.playground.database.User


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val db = Room.databaseBuilder(applicationContext, DatabaseApp::class.java, "database-name")
        .build()

    db.userDao.insert(User(1, "peter", "tux"))
  }
}
