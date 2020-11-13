package com.haroldcalayan.feature.master

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.R
import com.haroldcalayan.data.model.Movie
import com.haroldcalayan.databinding.AdapterMovieBinding
import com.haroldcalayan.feature.detail.DetailActivity
import com.haroldcalayan.feature.detail.DetailFragment
import com.haroldcalayan.utils.JsonUtils

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder> {

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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = movies[position]
        holder.bind(item)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = movies.size

    fun updateList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AdapterMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}