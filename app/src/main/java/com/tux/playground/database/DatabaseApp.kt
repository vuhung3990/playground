package com.tux.playground.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class DatabaseApp : RoomDatabase() {

  abstract val userDao: UserDao
}