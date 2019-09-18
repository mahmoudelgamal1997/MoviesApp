package com.example.moviesapp.MovieMVVM.data.Repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.popular_model.popular
import io.reactivex.disposables.CompositeDisposable

interface RepositInterface
{


    fun getPopularMovie( page:Int ):LiveData<popular>

    fun getMovieNetwork():LiveData<NetworkState>



}