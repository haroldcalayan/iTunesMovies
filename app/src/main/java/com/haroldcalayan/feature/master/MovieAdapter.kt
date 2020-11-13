package com.haroldcalayan.feature.master

import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.data.model.Movie

abstract class MovieAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val movies = mutableListOf<Movie>()

    open fun MovieAdapter2() {
        setHasStableIds(true)
    }

    open fun add(movie: Movie) {
        movies.add(movie)
        notifyDataSetChanged()
    }

    open fun add(index: Int, movie: Movie) {
        movies.add(index, movie)
        notifyDataSetChanged()
    }

    open fun addAll(collection: Collection<Movie>) {
        if (collection != null) {
            movies.addAll(collection)
            notifyDataSetChanged()
        }
    }

    open fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    open fun remove(movie: Movie) {
        movies.remove(movie)
        notifyDataSetChanged()
    }

    open fun getMovie(position: Int): Movie {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return getMovie(position).hashCode().toLong()
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}