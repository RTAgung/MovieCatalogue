package com.example.moviecatalogue.ui.detail.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.example.moviecatalogue.utils.ConstantValue.IMAGE_URL
import com.example.moviecatalogue.utils.Helper.runtimeFormatting
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_detail_movie.*

class DetailMovieFragment() : Fragment() {
    private lateinit var movie: MovieEntity
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            populateMovie(movie)

//            progress =
//                (context as DetailActivity).findViewById(R.id.progress_bar_detail)
//
//            if (arguments != null) {
//                val id = arguments?.getString(EXTRA_ID).toString()
//
//                val factory = ViewModelFactory.getInstance(requireActivity())
//                val viewModel = ViewModelProvider(
//                    this,
//                    factory
//                )[DetailMovieViewModel::class.java]
//
//                viewModel.setMovieId(id)
//
//                viewModel.movie.observe(this, { movie ->
//                    if (movie != null) {
//                        when (movie.status) {
//                            Status.LOADING -> progress.visibility = View.VISIBLE
//                            Status.SUCCESS -> if (movie.data != null) {
//                                progress.visibility = View.GONE
//                                populateMovie(movie.data)
//                            }
//                            Status.ERROR -> {
//                                progress.visibility = View.GONE
//                                Toast.makeText(
//                                    context,
//                                    resources.getString(R.string.error_message),
//                                    Toast.LENGTH_LONG
//                                ).show()
//                            }
//                        }
//                    }
//                })
//            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity?) {
        if (movieEntity != null) {
            tv_original_title.text = movieEntity.originalTitle
            tv_title.text = movieEntity.title
            tv_rate_avg.text = movieEntity.voteAverage.toString()
            val rateCount = "(${movieEntity.voteCount}) "
            tv_rate_count.text = rateCount
            tv_release.text =
                resources.getString(R.string.release_on_detail, movieEntity.releaseDate)
            tv_runtime.text = movieEntity.runtime?.let { runtimeFormatting(it) }
            tv_tagline.text = movieEntity.tagline
            tv_genres.text = movieEntity.genres
            tv_overview.text = movieEntity.overview

            context?.let {
                Glide.with(it)
                    .load(IMAGE_URL + movieEntity.backdropPath)
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_error)
                            .error(R.drawable.ic_error)
                    }
                    .into(iv_poster)
            }

            iv_share.setOnClickListener {
                onShareClicked(movieEntity.originalTitle)
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

    companion object {
        fun getInstance(movie: MovieEntity): DetailMovieFragment {
            val detailMovieFragment = DetailMovieFragment()
//            val bundle = Bundle()
//            bundle.putString(EXTRA_ID, id)
//            detailMovieFragment.arguments = bundle
            detailMovieFragment.movie = movie

            return detailMovieFragment
        }
    }
}