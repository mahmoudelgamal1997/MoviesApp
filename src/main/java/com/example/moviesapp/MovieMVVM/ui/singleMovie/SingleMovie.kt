package com.example.moviesapp.MovieMVVM.ui.singleMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviesapp.MovieMVVM.data.Repository.NetworkState
import com.example.moviesapp.MovieMVVM.data.Repository.Repositorin
import com.example.moviesapp.MovieMVVM.data.api.POSTER_BASE_URL
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBClient
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import com.example.moviesapp.R
import com.example.moviesapp.viewmodel
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovie : AppCompatActivity() {

    private lateinit var viewModel: viewmodel
    private lateinit var movieRepository: Repositorin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)

        val movieId: Int = intent.getIntExtra("id", 1)
        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()

        movieRepository = Repositorin(apiService)

        viewModel = viewmodel(movieRepository,movieId)

        viewModel.moviesDetailsRepository.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkStateRepository.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.FAILED) View.VISIBLE else View.GONE

        })

    }

    fun bindUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.voteAverage.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)


        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);


    }


}
