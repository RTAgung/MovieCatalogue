package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.TV_SHOW_DESTINATION
import com.example.moviecatalogue.utils.ConstantValue.IMAGE_URL
import kotlinx.android.synthetic.main.items_film.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    var listTvShows = ArrayList<TvShow>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TvShow) {
            with(itemView) {
                tv_item_original_title.text = tvShow.originalName
                tv_item_title.text = tvShow.name
                tv_item_release.text =
                    resources.getString(R.string.start_on, tvShow.firstAirDate)
                tv_item_rate.text = tvShow.voteAverage.toString()
                Glide.with(context)
                    .load(IMAGE_URL + tvShow.posterPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_item_poster)

                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_ID, tvShow.id)
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

    override fun getItemCount(): Int = listTvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShows[position])
    }
}