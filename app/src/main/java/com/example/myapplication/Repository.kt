package com.example.myapplication

import com.example.myapplication.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun searchMovies(searchText: String,key: String) = apiService.fetchMovies(searchText, key)
}