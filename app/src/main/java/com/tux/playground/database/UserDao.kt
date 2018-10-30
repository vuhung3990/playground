package com.tux.playground.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UserDao {

  @Insert
  fun insert(user: User): Single<Long>

  @Delete
  fun delete(user: User)

  @Query("SELECT * FROM users WHERE id in (:firstName)")
  fun findUserByFirstName(firstName: String): List<User>
}