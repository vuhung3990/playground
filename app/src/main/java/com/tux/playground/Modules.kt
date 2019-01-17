package com.tux.playground

import org.koin.dsl.module.module

class Modules {
  val userModule = module {
    single { User("peter tux") }
  }
}