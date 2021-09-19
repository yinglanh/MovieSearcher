package com.example.myapplication.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @Expose
    @SerializedName("Search")
    val searchMovies: List<Movie>,
    @Expose
    @SerializedName("totalResults")
    val totalResults: String,
    @Expose
    @SerializedName("Response")
    val response: String,
    @Expose
    @SerializedName("Error")
    val error: String
)
