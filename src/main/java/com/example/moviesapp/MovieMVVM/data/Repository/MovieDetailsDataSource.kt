package com.example.moviesapp.MovieMVVM.data.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsDataSource(val apiServices:TheMovieDBInterface , val compsit:CompositeDisposable )
{

    val _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
    get() = _networkState

    val _MoviesResponse =MutableLiveData<MovieDetails>()
    val downloadMovieResponse : LiveData<MovieDetails>
        get() = _MoviesResponse


    fun fetchMovieDetails(movieId:Int){


        _networkState.postValue(NetworkState.LOADING)

        try {

            compsit.add(
                apiServices.loadData(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                    {
                    _MoviesResponse.postValue(it)
                    _networkState.postValue(NetworkState.LOADED)

                    },

                    {
                        _networkState.postValue(NetworkState.FAILED)

                        Log.e("ERROR",it.message)


                    }
                    ))

        }catch (ex:Exception){

            Log.e("CATCh",ex.message)

        }
    }


}