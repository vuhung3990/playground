package com.tux.playground.detail

import android.app.Activity

class DetailPresenter(val view: DetailContract.View) : DetailContract.Presenter,
    DetailContract.InteractorOutput {

  private val interactor = DetailInteractor(this)
  private val route = DetailRoute(view as Activity)
}