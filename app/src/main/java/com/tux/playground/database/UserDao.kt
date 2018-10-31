package com.tux.playground.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Long

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users WHERE first_name in (:firstName)")
    fun findUserByFirstName(firstName: String): Single<List<User>>
}