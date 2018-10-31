package com.tux.playground

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tux.playground.database.DatabaseApp
import com.tux.playground.database.User
import com.tux.playground.database.UserDao
import io.reactivex.Maybe
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var mUserDao: UserDao
    private lateinit var mDb: DatabaseApp
    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        mDb = Room.inMemoryDatabaseBuilder(appContext, DatabaseApp::class.java).build()
        mUserDao = mDb.userDao
    }

    @Test
    fun testInsertDb() {
        val firstName = "peter"
        val lastName = "tux"
        val user = User(0, firstName, lastName)

        Observable
                // insert data
                .fromCallable { mUserDao.insert(user) }
                // get data
                .flatMapSingle { mUserDao.findUserByFirstName(firstName) }
                .test()
                .assertValue {
                    it[0].firstName == firstName && it[0].lastName == lastName
                }
    }

    @Test
    fun offlineFirst() {
        val listUser = listOf(
                User(firstName = "hung", lastName = "vu"),
                User(firstName = "peter", lastName = "tux"),
                User(firstName = "hieu", lastName = "nguyen")
        )

        // for case 2
        // mUserDao.insert(listUser)
        // case1 no data
        // 1. get data from db
        mUserDao.findUsers()
                .filter {
                    val shouldRequestApi = it.isEmpty()

                    // case2 data exists => just take it
                    if (!shouldRequestApi) {
                        println("case 2: have data")
                        println(it.toString())
                    }
                    return@filter shouldRequestApi
                }
                // 2. query api -> parse to list object
                // 3. save into db
                .flatMap {
                    Maybe.fromCallable { mUserDao.insert(listUser) }
                }
                .flatMapSingle {
                    mUserDao.findUsers()
                }
                .test()
                // 3 item from db
                .assertValue {
                    println("case 1: no data")
                    println(it.toString())
                    it.size == 3
                }

        // case exist data
    }
}
