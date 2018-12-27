package com.tux.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tux.playground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  val adapter = SimpleAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    binding.viewModel = viewModel
    binding.setLifecycleOwner(this)

    setupRecyclerView(binding.recyclerView, LinearLayoutManager(this), adapter)
    viewModel.data.observe(this, onDataChange())
  }

  private fun onDataChange() = Observer<List<String>> {
    adapter.replace(it)
  }

  private fun setupRecyclerView(view: RecyclerView, layoutManager: LinearLayoutManager, adapter: SimpleAdapter) {
    view.setHasFixedSize(true)
    view.layoutManager = layoutManager
    view.adapter = adapter
  }
}
