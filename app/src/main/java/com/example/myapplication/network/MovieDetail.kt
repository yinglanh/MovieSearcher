package com.example.myapplication.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @Expose
    @SerializedName("Title")
    val title: String,
    @Expose
    @SerializedName("Year")
    val year: String,
    @Expose
    @SerializedName("Poster")
    val poster: String,
    @Expose
    @SerializedName("Runtime")
    val runtime: String,
    @Expose
    @SerializedName("Actors")
    val actors: String,
    @Expose
    @SerializedName("Plot")
    val plot: String,
    @Expose
    @SerializedName("imdbID")
    val imdbID: String,
    @Expose
    @SerializedName("Response")
    val response: String,
)




