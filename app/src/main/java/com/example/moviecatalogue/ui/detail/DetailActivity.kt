package com.example.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.detail.movie.DetailMovieFragment
import com.example.moviecatalogue.ui.detail.tvshow.DetailTvShowFragment

class DetailActivity : AppCompatActivity() {
    private lateinit var id: String
    private lateinit var destination: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null) {
            with(intent) {
                id = getStringExtra(EXTRA_ID) as String
                destination = getStringExtra(EXTRA_DESTINATION) as String
            }
        }

        if (destination == MOVIE_DESTINATION) {
            showFragment(id, DetailMovieFragment())
        } else if (destination == TV_SHOW_DESTINATION) {
            showFragment(id, DetailTvShowFragment())
        }
    }

    private fun showFragment(id: String, fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString(EXTRA_ID, id)

        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.detail_frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DESTINATION = "extra_destination"
        const val MOVIE_DESTINATION = "movie_destination"
        const val TV_SHOW_DESTINATION = "tv_show_destination"
    }
}