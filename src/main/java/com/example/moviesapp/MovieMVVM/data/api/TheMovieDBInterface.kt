package com.example.moviesapp.MovieMVVM.data.api

import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import com.example.moviesapp.MovieMVVM.popular_model.Result
import com.example.moviesapp.MovieMVVM.popular_model.popular
import io.reactivex.Single
import io.reactivex.SingleObserver
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface
{



    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page:Int):Single<popular>

    @GET("movie/{movie_id}")
    fun loadData(@Path("movie_id") id:Int):Single<MovieDetails>


}