package com.example.newsapp

import android.app.Application
import com.example.newsapp.repository.MainRepository

class MainApplication : Application() {
    lateinit var mainRepository : MainRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }


    private fun initialize() {
        mainRepository = MainRepository()
    }
}