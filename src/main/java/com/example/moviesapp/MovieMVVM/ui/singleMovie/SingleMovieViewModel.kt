package com.example.moviesapp.MovieMVVM.ui.singleMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.MovieMVVM.data.Repository.NetworkState
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel (private val movieRepository:MoviesDetailsRepository,id:Int): ViewModel() {


    private val compositeDisposable =CompositeDisposable()

    val moviesDetailsRepository:LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable,id)

    }


    val networkState:LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()

    }
}