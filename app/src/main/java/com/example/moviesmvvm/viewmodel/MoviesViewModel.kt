package com.example.moviesmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesmvvm.model.MoviesResponse
import com.example.moviesmvvm.model.Network

class MoviesViewModel(val baseUrl: String) : ViewModel() {
    private val dataSet: MutableLiveData<MoviesResponse> =
        MutableLiveData()

    private val dataErrorMessage:
            MutableLiveData<String> = MutableLiveData()

    fun getDataSet(): LiveData<MoviesResponse> {
        return dataSet
    }

    fun getErrorMessage() : LiveData<String> {
        return dataErrorMessage
    }

    fun getMovies() {
        val network = Network(this)
        network.initRetrofit(baseUrl)
    }

    fun getMoviesData(dataSet: MoviesResponse) {
        this.dataSet.value = dataSet
    }

    fun getErrorMessage(errorMessage: String) {
        dataErrorMessage.value = errorMessage
    }
}