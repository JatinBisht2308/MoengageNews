package com.example.newsapp.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.NewsAPIRecord
import com.example.newsapp.models.RequestResult
import com.example.newsapp.models.UiState
import com.example.newsapp.repository.MainRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _newsArticles = MutableLiveData<UiState>(UiState.Loading)
    val newsArticles: LiveData<UiState>
        get() = _newsArticles

    init {
        viewModelScope.launch {
            try {
                _newsArticles.postValue(UiState.Loading)
                when (val result = repository.makeGetApiRequest()) {
                    is RequestResult.Success -> {
                        _newsArticles.postValue(UiState.Success(result.data))
                    }

                    is RequestResult.Error -> {
                        _newsArticles.postValue(UiState.Error(result.message))
                    }

                    else -> {
                        //Do nothing
                    }
                }
            } catch (ioException: Exception) {
                _newsArticles.postValue(UiState.Error(ioException.message.toString()))
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortNewsItemFromNewToOld() {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        when(val res = _newsArticles.value) {
            is UiState.Success -> {
                _newsArticles.postValue(UiState.Success(NewsAPIRecord(res.data.articles.sortedBy { LocalDate.parse(it.updatedAt, dateTimeFormatter) })))
            }
            else -> {
                _newsArticles
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortNewsItemFromOldToNew() {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        when(val res = _newsArticles.value) {
            is UiState.Success -> {
                _newsArticles.postValue(UiState.Success(NewsAPIRecord(res.data.articles.sortedByDescending{ LocalDate.parse(it.updatedAt, dateTimeFormatter) })))
            }
            else -> {
                _newsArticles
            }
        }
    }
}