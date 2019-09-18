package com.example.moviesapp.ui.MainActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.MovieMVVM.data.api.POSTER_BASE_URL
import com.example.moviesapp.MovieMVVM.popular_model.popular
import com.example.moviesapp.MovieMVVM.ui.singleMovie.SingleMovie
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.movie_list_item.view.*

class RecyclerPrepare(val items: popular, val context: Context): RecyclerView.Adapter<RecyclerPrepare.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder
    { return viewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list_item , parent , false)) }

    override fun getItemCount(): Int
    { return items.results.size }

    override fun onBindViewHolder(holder: viewHolder, position: Int)
    {

        var temp=items.results.get(position)
        holder?.name?.text = temp.title
        holder?.releaseDate.text= temp.releaseDate


        val moviePosterURL = POSTER_BASE_URL + temp?.posterPath
        Glide.with(holder.itemView.context).load(moviePosterURL).into(holder?.img)


        holder.itemView.setOnClickListener({


            var MovieId = temp.id
            var MoviePosterPath= temp.posterPath

            val intent=Intent(holder.itemView.context,SingleMovie::class.java)
            intent.putExtra("id",MovieId)
            intent.putExtra("posterPath",MoviePosterPath)

            holder.itemView.context.startActivity(intent)


        })


    }


    class viewHolder (view:View) : RecyclerView.ViewHolder(view)
    {

        val name = view.cv_movie_title
        val releaseDate = view.cv_movie_release_date
        val img=view.cv_iv_movie_poster

    }
}