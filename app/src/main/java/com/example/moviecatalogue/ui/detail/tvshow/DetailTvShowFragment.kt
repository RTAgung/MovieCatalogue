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
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.ConstantValue
import com.example.moviecatalogue.utils.Helper.genresFormatting
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

    private fun populateTvShow(tvShow: TvShow?) {
        if (tvShow != null) {
            tv_original_title.text = tvShow.originalName
            tv_title.text = tvShow.name
            tv_rate_avg.text = tvShow.voteAverage.toString()
            tv_rate_count.text = tvShow.voteCount.toString()
            tv_release.text = resources.getString(R.string.start_on_detail, tvShow.firstAirDate)
            tv_episodes.text = resources.getString(R.string.episodes, tvShow.numberOfEpisodes)
            tv_seasons.text = resources.getString(R.string.seasons, tvShow.numberOfSeasons)
            tv_genres.text = tvShow.genres?.let { genresFormatting(it) }
            tv_overview.text = tvShow.overview

            context?.let {
                Glide.with(it)
                    .load(ConstantValue.IMAGE_URL + tvShow.backdropPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_poster)
            }

            iv_share.setOnClickListener {
                onShareClicked(tvShow.originalName)
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