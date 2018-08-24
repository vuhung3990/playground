package com.tux.playground

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.factory
import org.kodein.di.generic.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), KodeinAware, Callback<List<Repo>> {
  override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
    t?.printStackTrace()
  }

  override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
    Log.d("debug", "success: ${response?.body()}")
  }

  override val kodein: Kodein by closestKodein()
  val githubService by instance<GitHubService>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val factoryString by factory<Int, String>("factory")
    Log.d("debug", ": ${factoryString(5)}")

    val providerString by instance<String>("provider")
    Log.d("debug", ": $providerString")

    // need github service
    val repos = githubService.listRepos("octocat")
    repos.enqueue(this)
  }
}
