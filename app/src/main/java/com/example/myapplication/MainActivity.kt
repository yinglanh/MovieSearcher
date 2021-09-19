package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.movieListLiveData.observe(this, {
            if (it.response == "True"){
                updateRecyclerView()
            }
        })
    }

    private fun updateRecyclerView(){
        val recyclerViewRecipes = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = viewModel.movieListLiveData.value?.let { MovieAdapter(it.searchMovies) }
        recyclerViewRecipes.adapter = adapter
        val layoutManage = LinearLayoutManager(this)
        recyclerViewRecipes.layoutManager = layoutManage
    }
}