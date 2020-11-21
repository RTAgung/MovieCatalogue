package com.example.moviecatalogue.ui.home.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.home.HomeViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                requireActivity(),
                factory
            )[HomeViewModel::class.java]

            val adapter = FavoriteAdapter()

            progress_bar_favorite.visibility = View.VISIBLE
            viewModel.getFavorites().observe(this, { favorites ->
                progress_bar_favorite.visibility = View.GONE
                adapter.submitList(favorites)
                adapter.notifyDataSetChanged()
            })

            with(rv_favorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
}