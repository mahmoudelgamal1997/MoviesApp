package com.example.moviesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.MovieMVVM.data.Repository.RepositPopularMovie
import com.example.moviesapp.MovieMVVM.popular_model.popular
import io.reactivex.disposables.CompositeDisposable

class popularViewModel (repo :RepositPopularMovie):ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val popularData :LiveData<popular> by lazy {
        repo.getPopularMovie(1)
    }

}