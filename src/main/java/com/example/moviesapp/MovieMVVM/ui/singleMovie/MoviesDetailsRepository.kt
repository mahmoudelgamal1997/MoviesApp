package com.example.moviesapp.MovieMVVM.ui.singleMovie

import androidx.lifecycle.LiveData
import com.example.moviesapp.MovieMVVM.data.Repository.MovieDetailsDataSource
import com.example.moviesapp.MovieMVVM.data.Repository.NetworkState
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MoviesDetailsRepository(private val apiService : TheMovieDBInterface) : RepositoryInterface {


    lateinit var movieDetailsNetworkDataSource: MovieDetailsDataSource

    override fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadMovieResponse
    }

    override fun getMovieDetailsNetworkState(): LiveData<NetworkState> {

        return movieDetailsNetworkDataSource.networkState
    }


    /*
    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int): LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }

*/
}
