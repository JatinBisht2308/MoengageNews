package com.example.newsapp.models

import java.util.Date

data class NewsAPIRecord(
    val articles : List<NewsArticle>
)

data class NewsArticle(
    val headline : String,
    val imgUrl : String,
    val url : String,
    val src : String,
    val updatedAt : String
)