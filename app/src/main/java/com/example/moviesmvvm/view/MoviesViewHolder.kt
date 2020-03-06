package com.example.moviesmvvm.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesmvvm.R
import com.example.moviesmvvm.model.MoviesPoko
import kotlinx.android.synthetic.main.movies_layout.view.*

class MoviesViewHolder(itemView: View):
    RecyclerView.ViewHolder(itemView){
    val ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
    val tvMovieTitle: TextView = itemView.findViewById(R.id.tv_movie_title)
    val tvMovieGenre: TextView = itemView.findViewById(R.id.tv_movie_genre)
    val tvMovieYear: TextView = itemView.findViewById(R.id.tv_movie_year)

    fun onBind(item: MoviesPoko) {
        tvMovieGenre.text = item.genre
        tvMovieTitle.text = item.title
        tvMovieYear.text = item.year
        Glide.with(itemView).load(item.poster).into(ivMoviePoster)
    }
}