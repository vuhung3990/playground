package com.tux.playground.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("v2/{id}")
    fun getListUser(@Path("id") id: String): Single<Users>
}