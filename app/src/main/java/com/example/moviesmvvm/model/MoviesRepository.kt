package com.example.moviesmvvm.model

import android.content.Context
import android.net.ConnectivityManager
import com.example.moviesmvvm.R
import com.example.moviesmvvm.view.CustomApplication
import com.example.moviesmvvm.viewmodel.MoviesViewModel
import java.util.*


class MoviesRepository (private val moviesViewModel: MoviesViewModel) {

    val daoMovies = MoviesDatabase.getInstance(
        CustomApplication.getCustomApp()
    ).getDao()

    val network : Network by lazy {
        Network(moviesViewModel)
    }

    //todo check for offline connection
    //todo check for cache (10 minutes)
    //todo retrieve data

    /**
     * Return true if offline
     * Return false if online
     */
    fun getOfflineMode(): Boolean{
        val context: Context = CustomApplication.getCustomApp()
        val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo.isConnected
    }

    /**
     * Return true if a network request was
     * last performed less than 10 minutes ago.
     *
     * Return false if network request was
     * last performed more than 10 minutes ago.
     */
    fun checkPreviousNetworkRequest(): Boolean {
        return (Calendar.getInstance().timeInMillis - getSpTime()) <
                (1000 * 60 * 10)
    }

    private fun saveSpTime(){
        CustomApplication.getCustomApp()
            .getSharedPreferences(CustomApplication.getCustomApp()
                .getString(R.string.TimeNetworkTracker),
                Context.MODE_PRIVATE)
            .edit()
            .putLong(CustomApplication.getCustomApp()
                .getString(R.string.LAST_NETWORK_REQUEST),
            Calendar.getInstance().timeInMillis)
            .commit()
    }

    private fun getSpTime() =
        CustomApplication.getCustomApp()
            .getSharedPreferences(CustomApplication.getCustomApp()
                .getString(R.string.TimeNetworkTracker),
            Context.MODE_PRIVATE)
            .getLong(CustomApplication.getCustomApp().getString(R.string.LAST_NETWORK_REQUEST), 0)

    fun getMoviesData(){
        if (getOfflineMode()) {
            //todo check cache time
            if (checkPreviousNetworkRequest()) {
                val listEntities = daoMovies.getDataFromCache()
                //todo parse the entities into MoviesPoko

                moviesViewModel.getMoviesData()
            } else {
                //todo display nothing
                moviesViewModel.getErrorMessage("No saved data")
            }
        }else{
            //todo check cache time
            if (checkPreviousNetworkRequest()){
                //todo read from cache
            }else{
                //todo save in cache from network
                network.initRetrofit()
            }
        }
    }
}