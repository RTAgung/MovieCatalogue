package com.example.moviecatalogue.ui.detail.movie

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
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.example.moviecatalogue.utils.ConstantValue.IMAGE_URL
import com.example.moviecatalogue.utils.Helper.genresFormatting
import com.example.moviecatalogue.utils.Helper.runtimeFormatting
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail_movie.*

class DetailMovieFragment : Fragment() {
    private lateinit var progress: ProgressBar
    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress =
                (context as DetailActivity).findViewById(R.id.progress_bar_detail)
            progress.visibility = View.VISIBLE

            if (arguments != null)
                id = arguments?.getString(EXTRA_ID).toString()

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                this,
                factory
            )[DetailMovieViewModel::class.java]

            viewModel.setMovieId(id)

            viewModel.getMovie().observe(this, { movie ->
                progress.visibility = View.GONE
                populateMovie(movie)
            })
        }
    }

    private fun populateMovie(movie: Movie?) {
        if (movie != null) {
            tv_original_title.text = movie.originalTitle
            tv_title.text = movie.title
            tv_rate_avg.text = movie.voteAverage.toString()
            tv_rate_count.text = movie.voteCount.toString()
            tv_release.text = resources.getString(R.string.release_on_detail, movie.releaseDate)
            tv_runtime.text = movie.runtime?.let { runtimeFormatting(it) }
            tv_tagline.text = movie.tagline
            tv_genres.text = movie.genres?.let { genresFormatting(it) }
            tv_overview.text = movie.overview

            context?.let {
                Glide.with(it)
                    .load(IMAGE_URL + movie.backdropPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_poster)
            }

            iv_share.setOnClickListener {
                onShareClicked(movie.originalTitle)
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