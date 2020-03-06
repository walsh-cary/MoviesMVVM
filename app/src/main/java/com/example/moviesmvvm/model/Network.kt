package com.example.moviesmvvm.model

import com.example.moviesmvvm.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network (val viewModel: MoviesViewModel) {

    fun initRetrofit(baseUrl: String) {
        getApi(baseUrl).getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                viewModel.getErrorMessage(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.let { viewModel.getMoviesData(it) }
            }
        })
    }

    fun getApi(url: String) : MoviesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(MoviesApi::class.java)
    }
}