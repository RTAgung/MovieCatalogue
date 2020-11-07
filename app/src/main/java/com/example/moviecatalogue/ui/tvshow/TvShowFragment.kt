package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.TvShow
import com.example.moviecatalogue.ui.home.HomeViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            progress_bar_tv_show.visibility = View.VISIBLE

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(
                requireActivity(),
                factory
            )[HomeViewModel::class.java]

            val tvShowAdapter = TvShowAdapter()

            viewModel.getTrendingTvShow().observe(this, { tvShows ->
                progress_bar_tv_show.visibility = View.GONE
                val listTvShows = ArrayList<TvShow>()
                listTvShows.addAll(tvShows)
                tvShowAdapter.listTvShows = listTvShows
            })

            with(rv_tv_show) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}