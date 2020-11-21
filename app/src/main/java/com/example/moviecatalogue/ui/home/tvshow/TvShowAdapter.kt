package com.example.moviecatalogue.ui.home.tvshow

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
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.TV_SHOW_DESTINATION
import com.example.moviecatalogue.utils.ConstantValue.IMAGE_URL
import kotlinx.android.synthetic.main.items_film.view.*

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(itemView) {
                tv_item_original_title.text = tvShowEntity.originalName
                tv_item_title.text = tvShowEntity.name
                tv_item_release.text =
                    resources.getString(R.string.start_on, tvShowEntity.firstAirDate)
                tv_item_rate.text = tvShowEntity.voteAverage.toString()
                Glide.with(context)
                    .load(IMAGE_URL + tvShowEntity.posterPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_item_poster)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, tvShowEntity.id)
                        putExtra(DetailActivity.EXTRA_DESTINATION, TV_SHOW_DESTINATION)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_film, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null)
            holder.bind(tvShow)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(
                oldItem: TvShowEntity,
                newItem: TvShowEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TvShowEntity,
                newItem: TvShowEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}