package com.tux.playground

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tux.playground.database.DatabaseApp
import com.tux.playground.database.User
import com.tux.playground.database.UserDao
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
}
