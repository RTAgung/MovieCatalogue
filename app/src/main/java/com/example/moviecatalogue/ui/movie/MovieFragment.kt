package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.Movie
import com.example.moviecatalogue.ui.home.HomeViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar_movie.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                requireActivity(),
                factory
            )[HomeViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getTrendingMovie().observe(this, { movies ->
                progress_bar_movie.visibility = View.GONE
                val listMovies = ArrayList<Movie>()
                listMovies.addAll(movies)
                movieAdapter.listMovies = listMovies
            })

            with(rv_movie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}