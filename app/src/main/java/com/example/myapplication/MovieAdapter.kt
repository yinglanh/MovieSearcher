package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.network.Movie

class MovieAdapter(
    private val myMovieClickListener: MovieClickListener,
    private val movies: List<Movie>
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val movieName = itemView.findViewById<TextView>(R.id.tv_movie_name)
            val tvYear = itemView.findViewById<TextView>(R.id.tv_year)
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            imageView.load(movie.poster)
            movieName.text = movie.title
            tvYear.text = movie.year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(movies[position])

        holder.itemView.setOnClickListener {
            Log.e("onMovieClick","${movies[position]}")
            myMovieClickListener.onMovieClickListener(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}