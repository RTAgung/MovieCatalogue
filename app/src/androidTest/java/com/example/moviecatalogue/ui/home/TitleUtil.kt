package com.example.moviecatalogue.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import com.example.moviecatalogue.R
import org.hamcrest.Matcher


object TitleUtil {
    fun getMovieTitle(matcher: ViewInteraction, position: Int): String {
        var movieTitle = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(RecyclerView::class.java)
            }

            override fun getDescription(): String {
                return "Title of current RecyclerView"
            }

            override fun perform(uiController: UiController, view: View) {
                val rv = view as RecyclerView
                movieTitle =
                    rv.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(
                        R.id.tv_item_original_title
                    )?.text.toString()
            }
        })
        return movieTitle
    }

    fun getTvShowTitle(matcher: ViewInteraction, position: Int): String {
        var tvShowTitle = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(RecyclerView::class.java)
            }

            override fun getDescription(): String {
                return "Title of current RecyclerView"
            }

            override fun perform(uiController: UiController, view: View) {
                val rv = view as RecyclerView
                tvShowTitle =
                    rv.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(
                        R.id.tv_item_original_title
                    )?.text.toString()
            }
        })
        return tvShowTitle
    }

    fun getFavoriteTitle(matcher: ViewInteraction, position: Int): String {
        var favoriteTitle = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(RecyclerView::class.java)
            }

            override fun getDescription(): String {
                return "Title of current RecyclerView"
            }

            override fun perform(uiController: UiController, view: View) {
                val rv = view as RecyclerView
                favoriteTitle =
                    rv.findViewHolderForAdapterPosition(position)?.itemView?.findViewById<TextView>(
                        R.id.tv_favorite_original_title
                    )?.text.toString()
            }
        })
        return favoriteTitle
    }
}