package com.example.moviecatalogue.ui.detail.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.ConstantValue
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail_tv_show.*

class DetailTvShowFragment : Fragment() {
    private lateinit var progress: ProgressBar
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress =
                (context as DetailActivity).findViewById(R.id.progress_bar_detail)
            progress.visibility = View.VISIBLE

            if (arguments != null)
                id = arguments?.getString(DetailActivity.EXTRA_ID).toString()

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[DetailTvShowViewModel::class.java]

            viewModel.setTvShowId(id)

            viewModel.getTvShow().observe(this, { tvShow ->
                progress.visibility = View.GONE
                populateTvShow(tvShow)
            })
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity?) {
        if (tvShowEntity != null) {
            tv_original_title.text = tvShowEntity.originalName
            tv_title.text = tvShowEntity.name
            tv_rate_avg.text = tvShowEntity.voteAverage.toString()
            val rateCount = "(${tvShowEntity.voteCount}) "
            tv_rate_count.text = rateCount
            tv_release.text = resources.getString(R.string.start_on_detail, tvShowEntity.firstAirDate)
            tv_episodes.text =
                tvShowEntity.numberOfEpisodes?.let {
                    resources.getQuantityString(
                        R.plurals.episodes,
                        it,
                        it
                    )
                }
            tv_seasons.text =
                tvShowEntity.numberOfSeasons?.let {
                    resources.getQuantityString(
                        R.plurals.seasons,
                        it,
                        it
                    )
                }
            tv_genres.text = tvShowEntity.genres
            tv_overview.text = tvShowEntity.overview

            context?.let {
                Glide.with(it)
                    .load(ConstantValue.IMAGE_URL + tvShowEntity.backdropPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_poster)
            }

            iv_share.setOnClickListener {
                onShareClicked(tvShowEntity.originalName)
            }
        }
    }

    private fun onShareClicked(title: String?) {
        if (activity != null) {
            val mimeType = "text/plain"
            activity?.let {
                ShareCompat.IntentBuilder.from(it).apply {
                    setType(mimeType)
                    setChooserTitle("Share now")
                    setText(resources.getString(R.string.share_text, title))
                    startChooser()
                }
            }
        }
    }
}