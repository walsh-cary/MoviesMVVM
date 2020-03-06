package com.example.moviesmvvm.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviesmvvm.R
import com.example.moviesmvvm.model.MoviesResponse
import com.example.moviesmvvm.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

//    val gViewModel: MoviesViewModel by lazy {
//        ViewModelProvider(
//            this,
//            object : ViewModelProvider.Factory{
//                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                    return MoviesViewModel("https://movies-sample.herokuapp.com/")
//                            as T
//                }
//            }
//        ).get(MoviesViewModel::class.java)
//    }

    val moviesAdapter : MoviesAdapter by lazy { MoviesAdapter() }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(movies_toolbar)

        recycler_view.layoutManager =
            GridLayoutManager(this, 3)
        recycler_view.adapter = MoviesAdapter()

        val viewModel =
            ViewModelProvider(
                this,
                object : ViewModelProvider.Factory{
                    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                        return MoviesViewModel("https://movies-sample.herokuapp.com/")
                        as T
                    }
                }
            ).get(MoviesViewModel::class.java)

        viewModel.getDataSet().observe(this,
        object : Observer<MoviesResponse>{
            override fun onChanged(t: MoviesResponse?) {
                moviesAdapter.dataSet = t

            }
        })

        viewModel.getMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onRefresh() {
        //todo implement onRefresh listener
    }
}
