package com.tux.playground

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(), KodeinAware {
  override val kodein = Kodein.lazy {
    bind<GsonConverterFactory>() with singleton { GsonConverterFactory.create() }
    bind<Retrofit>(tag = "github") with provider {
      Retrofit.Builder()
          .baseUrl("https://api.github.com/")
          .addConverterFactory(instance())
          .build()
    }
    bind<GitHubService>() with singleton {
      GithubModule(instance("github")).getGithubService()
    }
    bind<String>(tag = "provider") with provider { "String" }
    bind<String>(tag = "factory") with factory { number: Int -> "String $number" }
//    bind<GitHubService>() with singleton { NetModule(instance()) }
  }
}