package com.example.moviesmvvm.model

import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET("api/movies")
    fun getMovies(): Call<MoviesResponse>
}