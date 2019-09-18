package com.example.moviesapp.MovieMVVM.data.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.moviesapp.MovieMVVM.data.api.FIRST_PAGE
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.popular_model.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Pagination (private val apiServices:TheMovieDBInterface , private val compositeDisposable: CompositeDisposable): PageKeyedDataSource<Int,Result>() {

    private var page = FIRST_PAGE
    private val networkState = MutableLiveData<NetworkState>()



    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Result>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiServices.getPopularMovie(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.results,null,page+1)
                        networkState.postValue(NetworkState.LOADED)

                    }
                    ,
                    {

                        networkState.postValue(NetworkState.ENDOFLIST)
                        Log.e("message",it.message)


                    }
                )
        )


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {

        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiServices.getPopularMovie(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if(it.totalPages >= params.key) {
                            callback.onResult(it.results, params.key+1)
                            networkState.postValue(NetworkState.LOADED)
                        }
                        else{
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ENDOFLIST)
                        Log.e("MovieDataSource", it.message)
                    }

                )
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}