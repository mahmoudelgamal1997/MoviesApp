package com.example.moviesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.MovieMVVM.data.Repository.NetworkState
import com.example.moviesapp.MovieMVVM.data.Repository.Repositorin
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class viewmodel ( val movieRepository: Repositorin , id:Int) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()


    val moviesDetailsRepository: LiveData<MovieDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(id,compositeDisposable)

    }


    val networkStateRepository:LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }
}