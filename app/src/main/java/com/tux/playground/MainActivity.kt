package com.tux.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val simpleAdapter = SimpleListAdapter()
    rcv.layoutManager = LinearLayoutManager(this)
    rcv.adapter = simpleAdapter
    rcv.setHasFixedSize(true)

    // dump data
    val listData = (1..100).map { "item $it" }
    simpleAdapter.updateList(listData)

    val listAds = listOf(
        "https://www.designmantic.com/blog/wp-content/uploads/2016/05/house-stark.png",
        "https://www.designmantic.com/blog/wp-content/uploads/2016/05/house-lannister.png",
        "https://www.designmantic.com/blog/wp-content/uploads/2016/05/house-targaryen.png",
        "https://www.designmantic.com/blog/wp-content/uploads/2016/05/House-Barethon.jpg"
    )
    simpleAdapter.setBannerList(listAds)
  }
}
