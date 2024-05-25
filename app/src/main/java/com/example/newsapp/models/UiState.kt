package com.example.newsapp.models

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data : NewsAPIRecord) : UiState()
    data class Error(val message : String) : UiState()
}