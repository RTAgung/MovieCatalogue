package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.detail.movie.DetailMovieFragment
import com.example.moviecatalogue.ui.detail.tvshow.DetailTvShowFragment
import com.example.moviecatalogue.utils.ConstantValue.TYPE_MOVIE
import com.example.moviecatalogue.utils.ConstantValue.TYPE_TV_SHOW
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailActivityViewModel
    private lateinit var id: String
    private lateinit var destination: String
    private lateinit var type: String

    private var stateBoolean = false
    private var menu: Menu? = null
    private var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailActivityViewModel::class.java]

        if (intent != null) {
            id = intent.getStringExtra(EXTRA_ID) as String
            destination = intent.getStringExtra(EXTRA_DESTINATION) as String
            type = if (destination == MOVIE_DESTINATION) TYPE_MOVIE else TYPE_TV_SHOW

            viewModel.setId(id)
            viewModel.setType(type)

            if (destination == MOVIE_DESTINATION) {
                viewModel.movie.observe(this, { movie ->
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> progress_bar_detail.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                progress_bar_detail.visibility = View.GONE
                                isLoading = false
                                if (movie.data != null)
                                    showFragment(DetailMovieFragment.getInstance(movie.data))
                            }
                            Status.ERROR -> {
                                progress_bar_detail.visibility = View.GONE
                                isLoading = false
                                Toast.makeText(
                                    this,
                                    resources.getString(R.string.error_message),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                })
            } else if (destination == TV_SHOW_DESTINATION) {
                viewModel.tvShow.observe(this, { tvShow ->
                    if (tvShow != null) {
                        when (tvShow.status) {
                            Status.LOADING -> progress_bar_detail.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                progress_bar_detail.visibility = View.GONE
                                isLoading = false
                                if (tvShow.data != null)
                                    showFragment(DetailTvShowFragment.getInstance(tvShow.data))
                            }
                            Status.ERROR -> {
                                progress_bar_detail.visibility = View.GONE
                                isLoading = false
                                Toast.makeText(
                                    this,
                                    resources.getString(R.string.error_message),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.detail_frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.checkFavorite()?.observe(this, { state ->
            stateBoolean = if (state != null)
                state == 1
            else
                false
            favoriteState(stateBoolean)
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_favorite -> if (!isLoading) {
                if (stateBoolean)
                    viewModel.deleteFavorite()
                else
                    viewModel.insertFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_on)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_off)
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DESTINATION = "extra_destination"
        const val MOVIE_DESTINATION = "movie_destination"
        const val TV_SHOW_DESTINATION = "tv_show_destination"
    }
}