package com.tux.playground.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
class User(
        @Expose
        @PrimaryKey(autoGenerate = true) val id: Int? = null,

        @SerializedName("first_name")
        @ColumnInfo(name = "first_name") val firstName: String,

        @SerializedName("last_name")
        @ColumnInfo(name = "last_name") val lastName: String
)