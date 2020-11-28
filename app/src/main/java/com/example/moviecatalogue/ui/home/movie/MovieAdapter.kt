package com.example.moviecatalogue.ui.home.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_DESTINATION
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.MOVIE_DESTINATION
import com.example.moviecatalogue.utils.ConstantValue.IMAGE_URL
import kotlinx.android.synthetic.main.items_film.view.*

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieEntity: MovieEntity) {
            with(itemView) {
                tv_item_original_title.text = movieEntity.originalTitle
                tv_item_title.text = movieEntity.title
                tv_item_release.text =
                    resources.getString(R.string.release_on, movieEntity.releaseDate)
                tv_item_rate.text = movieEntity.voteAverage.toString()
                Glide.with(context)
                    .load(IMAGE_URL + movieEntity.posterPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_item_poster)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(EXTRA_ID, movieEntity.id)
                        putExtra(EXTRA_DESTINATION, MOVIE_DESTINATION)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_film, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
            holder.bind(movie)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}