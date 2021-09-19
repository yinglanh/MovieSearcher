package com.example.myapplication

import com.example.myapplication.network.Movie

interface MovieClickListener {
    fun onMovieClickListener(data: Movie)
}