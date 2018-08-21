package com.tux.playground

import retrofit2.Retrofit

class GithubModule(val retrofit: Retrofit) {
  fun getGithubService(): GitHubService {
    return retrofit.create(GitHubService::class.java)
  }
}