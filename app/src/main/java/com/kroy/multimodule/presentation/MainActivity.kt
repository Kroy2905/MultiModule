package com.kroy.multimodule.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kroy.multimodule.R
import com.kroy.multimodule.data.paging.PostPagingAdapter
import com.kroy.multimodule.databinding.ActivityMainBinding
import com.kroy.multimodule.domain.viewmodel.PostViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: PostPagingAdapter
    private lateinit var viewModel: PostViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PostPagingAdapter()
        viewModel = ViewModelProvider(this).get(PostViewmodel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        viewModel.list.observe(this) {
            adapter.submitData(lifecycle = lifecycle, it)
        }


    }
}