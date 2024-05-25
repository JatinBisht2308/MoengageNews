package com.example.newsapp

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.models.UiState
import com.example.newsapp.repository.MainRepository
import com.example.newsapp.viewmodels.MainViewModel
import com.example.newsapp.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity(), NewsItemListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsListAdapter
    private var mainViewModel: MainViewModel? = null
    private var mainRepository : MainRepository? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NewsListAdapter(arrayListOf(), this)

        binding.rvNewsArticles.layoutManager = LinearLayoutManager(this)
        binding.rvNewsArticles.adapter = adapter

        mainRepository = (application as MainApplication).mainRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository!!))[MainViewModel::class.java]
        initObservers()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initObservers() {
        binding.btNew.setOnClickListener {
            mainViewModel?.sortNewsItemFromNewToOld()
        }

        binding.btOld.setOnClickListener {
            mainViewModel?.sortNewsItemFromOldToNew()
        }

        mainViewModel?.newsArticles?.observe(this) {
            when (it){
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("MA", it.data.articles.toString())
                    adapter.submitList(it.data.articles)
                }

                else -> {
                    //Do nothing
                }
            }
        }
    }

    override fun onItemClicked(urlToNewsItem: String) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(urlToNewsItem))
    }
}