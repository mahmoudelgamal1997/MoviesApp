package com.example.moviesapp.MovieMVVM.data.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBInterface
import com.example.moviesapp.MovieMVVM.popular_model.popular
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositPopularMovie (private val compsit:CompositeDisposable,private val apiInterface:TheMovieDBInterface) : RepositInterface
{


    val _popularResponse = MutableLiveData <popular>()
    val popularResponse:LiveData<popular>
    get() = _popularResponse


    override fun getPopularMovie(page: Int): LiveData<popular> {

        try {
            compsit.add(
                apiInterface.getPopularMovie(1)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _popularResponse.postValue(it)
                        }
                        ,
                        {


                        }
                    )
            )


        }catch (e:Exception){

        }

        return popularResponse
    }

    override fun getMovieNetwork(): LiveData<NetworkState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
