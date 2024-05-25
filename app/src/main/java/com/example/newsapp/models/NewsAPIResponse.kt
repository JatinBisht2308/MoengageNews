package com.example.newsapp.models

data class NewsAPIResponse(
    val articles: List<Article>,
    val status: String
)