package com.tux.playground

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : KoinTest {
  val user by inject<User>()

  @Before
  fun setUp() {
    startKoin(listOf(Modules().userModule))
  }

  @After
  fun after() {
    stopKoin()
  }

  @Test
  fun testUserDependence() {
    assertEquals(user.name, "peter tux")
  }

  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }
}
