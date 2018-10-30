package com.tux.playground

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.tux.playground.database.DatabaseApp
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import com.tux.playground.database.UserDao
import org.junit.Before
import androidx.room.Room
import com.tux.playground.database.User
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  private var mUserDao: UserDao? = null
  private var mDb: DatabaseApp? = null
  @Before
  fun setUp() {
    val appContext = InstrumentationRegistry.getTargetContext()
    mDb = Room.inMemoryDatabaseBuilder(appContext, DatabaseApp::class.java).build()
    mUserDao = mDb?.userDao
  }

  @Test
  fun useAppContext() {
    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()
    assertEquals("com.tux.playground", appContext.packageName)
  }

  @Test
  fun testInsertDb() {
    val firstName = "peter"
    val lastName = "tux"
    val user = User(0, firstName, lastName)

    // insert data
    mUserDao?.insert(user)

    // get data
    val listUser = mUserDao?.findUserByFirstName(firstName)

    assertThat(listUser?.get(0), equalTo(user))
  }
}
