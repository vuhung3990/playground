package com.tux.playground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.tux.playground.api.UserService
import com.tux.playground.database.DatabaseApp
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // db setup
        val database = Room.databaseBuilder(applicationContext, DatabaseApp::class.java, "my_db").build()
        val userDao = database.userDao

        // api setup
        val retrofit = Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val userService = retrofit.create(UserService::class.java)

        // get users from db
        val flowGetUser = userDao.findUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter {
                    val shouldRequestApi = it.isEmpty()

                    // case2 data exists => just take it
                    if (!shouldRequestApi) {
                        Log.d("aaaaaa", "case 2")
                        Log.d("aaaaaa", it.toString())
                    }
                    // case 1 => request api
                    return@filter shouldRequestApi
                }
                // 2. query api -> parse to list object
                .flatMapSingle {
                    userService.getListUser("5bd9fb812f0000843706d3b3").subscribeOn(Schedulers.io())
                }
                .map {
                    it.users
                }
                // 3. save into db
                .flatMap {
                    Single.fromCallable { userDao.insert(it) }
                }
                .flatMap {
                    userDao.findUsers()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("aaaaaa", "case 1")
                    Log.d("aaaaaa", it.toString())
                }, {
                    it.printStackTrace()
                })

        flowGetUser.dispose()
    }
}
