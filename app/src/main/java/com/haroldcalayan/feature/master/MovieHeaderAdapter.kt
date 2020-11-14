package com.haroldcalayan.feature.master

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.R
import com.haroldcalayan.data.model.Movie
import com.haroldcalayan.databinding.AdapterMovieBinding
import com.haroldcalayan.feature.detail.DetailActivity
import com.haroldcalayan.feature.detail.DetailFragment
import com.haroldcalayan.utils.JsonUtils
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter
import java.lang.String


class MovieHeaderAdapter : MovieAdapter<MovieHeaderAdapter.MovieHeaderViewHolder>, StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {

    private lateinit var parentActivity: MasterActivity
    private lateinit var movies: List<Movie>
    private var twoPane: Boolean = false
    private val onClickListener: View.OnClickListener

    constructor(parentActivity: MasterActivity, values: List<Movie>, twoPane: Boolean) {
        this.parentActivity = parentActivity
        this.movies = values
        this.twoPane = twoPane
    }

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            val serializedItem = JsonUtils.getGson().toJson(item)
            if (twoPane) {
                val fragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(DetailFragment.ARG_ITEM, serializedItem)
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, DetailActivity::class.java).apply {
                    putExtra(DetailFragment.ARG_ITEM, serializedItem)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun getHeaderId(position: Int): Long {
        //return movies[position].trackName?.toLong() ?: 0
        //return if(position == 2) position.toLong() else 0
        return 1
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_header, parent, false)
        return object : RecyclerView.ViewHolder(view) { }
    }

    override fun getItemCount() = movies.size

    override fun onBindHeaderViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
        val textView = p0?.itemView as TextView
        textView.text = String.valueOf(getMovie(p1).userLastVisited)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHeaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater)
        return MovieHeaderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHeaderViewHolder, position: Int) {
        val item = movies[position]
        holder?.bind(item)
        holder?.itemView?.tag = item
        holder?.itemView?.setOnClickListener(onClickListener)
    }

    fun updateList(movies: List<Movie>) {
        this.movies = movies
        clear()
        addAll(movies)
    }

    inner class MovieHeaderViewHolder(val binding: AdapterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}