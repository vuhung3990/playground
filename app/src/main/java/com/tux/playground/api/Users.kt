package com.tux.playground.api

import com.google.gson.annotations.SerializedName
import com.tux.playground.database.User

data class Users(@SerializedName("users") val users: List<User>)