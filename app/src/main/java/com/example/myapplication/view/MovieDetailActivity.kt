package com.example.myapplication.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.network.MovieDetail

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val viewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val extras = intent.extras
        if (extras != null) {
            val id = extras.getString("id")
            if (id != null) {
                viewModel.searchMovieById(id)
            }
        }

        viewModel.movieLiveData.observe(this, {
            if (viewModel.movieLiveData.value?.response == "True") {
                updateMovieDetail(it)
            }
        })
    }

    private fun updateMovieDetail(movieDetail: MovieDetail){
        findViewById<TextView>(R.id.tv_movie_title).text = movieDetail.title
    }
}