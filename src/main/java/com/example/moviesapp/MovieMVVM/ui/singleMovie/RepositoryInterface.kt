package com.example.moviesapp.MovieMVVM.ui.singleMovie

import androidx.lifecycle.LiveData
import com.example.moviesapp.MovieMVVM.data.Repository.NetworkState
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

interface  RepositoryInterface
{
    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int): LiveData<MovieDetails>
    fun getMovieDetailsNetworkState(): LiveData<NetworkState>
    }