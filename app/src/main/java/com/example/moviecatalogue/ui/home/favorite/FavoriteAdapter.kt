package com.example.moviecatalogue.ui.home.favorite

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
import com.example.moviecatalogue.data.source.local.entity.FavoriteEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.ConstantValue
import com.example.moviecatalogue.utils.ConstantValue.TYPE_MOVIE
import kotlinx.android.synthetic.main.items_favorite.view.*

class FavoriteAdapter :
    PagedListAdapter<FavoriteEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favoriteEntity: FavoriteEntity) {
            with(itemView) {
                val date: String
                val destination: String
                if (favoriteEntity.type == TYPE_MOVIE) {
                    date = resources.getString(R.string.release_on, favoriteEntity.releaseDate)
                    destination = DetailActivity.MOVIE_DESTINATION
                } else {
                    date = resources.getString(R.string.start_on, favoriteEntity.releaseDate)
                    destination = DetailActivity.TV_SHOW_DESTINATION
                }

                tv_favorite_original_title.text = favoriteEntity.originalTitle
                tv_favorite_title.text = favoriteEntity.title
                tv_favorite_release.text = date
                tv_favorite_type.text = favoriteEntity.type

                Glide.with(context)
                    .load(ConstantValue.IMAGE_URL + favoriteEntity.posterPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_favorite_poster)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, favoriteEntity.id)
                        putExtra(DetailActivity.EXTRA_DESTINATION, destination)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favorite = getItem(position)
        if (favorite != null) {
            holder.bind(favorite)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteEntity,
                newItem: FavoriteEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}