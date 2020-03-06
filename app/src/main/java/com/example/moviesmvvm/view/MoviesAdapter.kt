package com.example.moviesmvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesmvvm.R
import com.example.moviesmvvm.model.MoviesResponse

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    var dataSet: MoviesResponse? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
            MoviesViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.movies_layout,
                parent,
                false))

    override fun getItemCount(): Int {
        return dataSet?.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        dataSet?.data?.get(position)?.let {
            holder.onBind(it)
        }
    }
}