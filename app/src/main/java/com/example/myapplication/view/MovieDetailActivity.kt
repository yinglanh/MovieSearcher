package com.example.myapplication.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MainViewModel
import com.example.myapplication.R
import com.example.myapplication.network.MovieDetail
import com.example.myapplication.databinding.ActivityMovieDetailBinding


class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMovieDetailBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_movie_detail
        )

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
                binding.movieDetail = it
            }
        })
    }
}