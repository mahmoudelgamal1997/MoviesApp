package com.example.moviesapp.MovieMVVM.data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBClient
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Repositorin(val api: TheMovieDBInterface) :RepositTest {



    lateinit var movieDetailsNetworkDataSource: MovieDetailsDataSource


    val _networkstate = MutableLiveData<NetworkState>()
    val networkState:LiveData<NetworkState>
    get() = _networkstate

    val _movieResponse = MutableLiveData<MovieDetails>()
    val movieResponse:LiveData<MovieDetails>
    get() = _movieResponse



    override fun fetchSingleMovieDetails(movieId: Int,  compsit:CompositeDisposable): LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsDataSource(api,compsit)


        _networkstate.postValue(NetworkState.LOADING)


        try {
            compsit.add(
                 api.loadData(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {

                            _movieResponse.postValue(it)
                            _networkstate.postValue(NetworkState.LOADED)

                        },
                        {
                         _networkstate.postValue(NetworkState.FAILED)
                        }))





        }catch (ex:Exception){

        }

        return movieResponse
    }

    override fun getMovieDetailsNetworkState(): LiveData<NetworkState> {

       return networkState

    }


}