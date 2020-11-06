package com.example.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DataDummy
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
    - Memberi tindakan klik pada data pertama di rv_movie
    - Memastikan TextView untuk original title tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk vote_average tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk release tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk overview tampil sesuai dengan yang diharapkan.
4. Menampilkan detail data tv show
    - Klik TabLayout dengan teks "TV SHOW"
    - Memberi tindakan klik pada data pertama di rv_tvshow
    - Memastikan TextView untuk original title tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk vote_average tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk start air tampil sesuai dengan yang diharapkan.
    - Memastikan TextView untuk overview tampil sesuai dengan yang diharapkan.
 */

class HomeActivityTest {
    private val dummyMovies = DataDummy.generateDummyMovie()
    private val dummyTvShows = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(dummyMovies[0].originalTitle)))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyMovies[0].title)))

        onView(withId(R.id.tv_rate_avg)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_avg)).check(matches(withText(dummyMovies[0].voteAverage)))

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText("Release on\n${dummyMovies[0].releaseDate}")))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyMovies[0].overview)))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_original_title)).check(matches(withText(dummyTvShows[0].originalName)))

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyTvShows[0].name)))

        onView(withId(R.id.tv_rate_avg)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_avg)).check(matches(withText(dummyTvShows[0].voteAverage)))

        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText("Start on\n${dummyTvShows[0].firstAirDate}")))

        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(withText(dummyTvShows[0].overview)))
    }
}