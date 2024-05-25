package com.example.newsapp

import android.util.Log
import com.example.newsapp.models.NewsAPIRecord
import com.example.newsapp.models.NewsAPIResponse
import com.example.newsapp.models.NewsArticle

object DataMapper {

    fun getNewsRecord(apiResponse: NewsAPIResponse): NewsAPIRecord {
        val newsArticles = arrayListOf<NewsArticle>()



        for (article in apiResponse.articles) {
            newsArticles.add(
                NewsArticle(
                    headline = article.title,
                    imgUrl = article.urlToImage,
                    url = article.url,
                    src = article.source.name,
                    updatedAt = article.publishedAt
                )
            )
        }
        return NewsAPIRecord(newsArticles)
    }
}