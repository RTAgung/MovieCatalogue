package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.EspressoIdlingResource.espressoTestIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
Scenario Instrumental Testing :
1. Menampilkan data daftar movie
    - Memastikan rv_movie dalam keadaan tampil
    - Gulir rv_movie ke dalam posisi terakhir
2. Menampilkan data daftar tv show
    - Klik TabLayout dengan teks "TV SHOW"
    - Memastikan rv_tvshow dalam keadaan tampil
    - Gulir rv_tvshow ke dalam posisi terakhir
3. Menampilkan detail data movie
    - Mengamambil string original title pada item pertama
    - Memberi tindakan klik pada data pertama di rv_movie
    - Memastikan TextView untuk original title tampil sesuai dengan yang diharapkan
4. Menampilkan detail data tv show
    - Klik TabLayout dengan teks "TV SHOW"
    - Mengamambil string original title pada item pertama
    - Memberi tindakan klik pada data pertama di rv_tv_show
    - Memastikan TextView untuk original title tampil sesuai dengan yang diharapkan.
 */

class HomeActivityTest {
    private lateinit var movieTitle: String
    private lateinit var tvShowTitle: String

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText(activityRule.activity.resources.getString(R.string.tv_show))).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(20)
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).also {
            movieTitle = TitleUtil.getMovieTitle(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(movieTitle)))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText(activityRule.activity.resources.getString(R.string.tv_show))).perform(click())
        onView(withId(R.id.rv_tv_show)).also {
            tvShowTitle = TitleUtil.getTvShowTitle(it, 0)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(tvShowTitle)))
    }
}