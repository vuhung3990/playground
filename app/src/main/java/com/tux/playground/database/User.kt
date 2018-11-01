package com.tux.playground.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [
  Index(value = ["first_name", "last_name"], unique = true)
])
class User(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String
)