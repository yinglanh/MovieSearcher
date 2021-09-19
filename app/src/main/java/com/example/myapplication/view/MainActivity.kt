package com.example.myapplication.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainViewModel
import com.example.myapplication.adapters.MovieAdapter
import com.example.myapplication.MovieClickListener
import com.example.myapplication.R
import com.example.myapplication.network.Movie


class MainActivity : AppCompatActivity(), MovieClickListener {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.et_search)
        etSearch.addTextChangedListener(textWatcher)

        viewModel.movieListLiveData.observe(this, {
            if (it.response == "True") {
                updateRecyclerView()
            } else {
                Log.e("result", it.toString())
//                if (it.error.isNotEmpty()){
//                    Toast.makeText(applicationContext, it.error, Toast.LENGTH_SHORT)
//                        .show()
//                }
            }
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        viewModel.mSearchText.value?.let { viewModel.searchMovies(it) }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            viewModel.searchMovies(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    private fun updateRecyclerView() {
        val recyclerViewRecipes = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = viewModel.movieListLiveData.value?.let { MovieAdapter(this, it.searchMovies) }
        recyclerViewRecipes.adapter = adapter
        val layoutManage = LinearLayoutManager(this)
        recyclerViewRecipes.layoutManager = layoutManage
    }

    override fun onMovieClickListener(data: Movie) {
//        Toast.makeText(this,"onMovieClickListener, $data", Toast.LENGTH_SHORT).show()

        val id = data.imdbID
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}