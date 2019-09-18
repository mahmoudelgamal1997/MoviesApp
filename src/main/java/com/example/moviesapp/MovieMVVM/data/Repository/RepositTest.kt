package com.example.moviesapp.MovieMVVM.data.Repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBClient
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

interface RepositTest{

    fun fetchSingleMovieDetails(movieId: Int, compsit:CompositeDisposable): LiveData<MovieDetails>
    fun getMovieDetailsNetworkState(): LiveData<NetworkState>

}