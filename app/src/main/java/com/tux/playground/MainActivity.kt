package com.tux.playground

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BaseQuickAdapter.OnItemClickListener {
  override fun onItemClick(adapter1: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    adapter.itemClick(position)
  }

  val adapter = MultipleItemQuickAdapter()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val dataTest = mutableListOf<MultipleItem>()
    dataTest.add(MultipleItem(VIEW_TYPE_1, "text 1"))
    dataTest.add(MultipleItem(VIEW_TYPE_1, "text 1"))
    dataTest.add(MultipleItem(VIEW_TYPE_1, "text 32"))
    dataTest.add(MultipleItem(VIEW_TYPE_1, "text 1"))

    adapter.replaceData(dataTest)

    list.setHasFixedSize(true)
    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter

    adapter.onItemClickListener = this
  }
}
