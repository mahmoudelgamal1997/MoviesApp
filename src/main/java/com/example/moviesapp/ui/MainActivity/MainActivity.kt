package com.example.moviesapp.ui.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.MovieMVVM.data.Repository.RepositPopularMovie
import com.example.moviesapp.MovieMVVM.data.api.TheMovieDBClient
import com.example.moviesapp.MovieMVVM.ui.singleMovie.SingleMovie
import com.example.moviesapp.R
import com.example.moviesapp.popularViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var model: popularViewModel

    private var comp=CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Creates a vertical Layout Manager
        rv_movie_list.layoutManager = LinearLayoutManager(this)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
        //        rv_animal_list.layoutManager = GridLayoutManager(this, 2)


        var api =TheMovieDBClient.getClient()

        var repo = RepositPopularMovie(comp,api)

        model = popularViewModel(repo)

        model.popularData.observe(this, Observer{

            // Access the RecyclerView Adapter and load the data into it
            rv_movie_list.adapter = RecyclerPrepare(it, this)


        })





    }


}

