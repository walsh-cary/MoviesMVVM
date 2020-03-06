package com.example.moviesmvvm.model

data class MoviesResponse(var data: List<MoviesPoko>)

data class MoviesPoko(var id: Int,
var title: String,
var year: String,
var poster: String,
var genre: String)
