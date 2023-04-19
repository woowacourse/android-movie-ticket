package woowacourse.movie.presentation.activities.movielist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.presentation.activities.custom.ClickViewAction.clickViewWithId
import woowacourse.movie.presentation.activities.custom.RecyclerViewAssertion.matchItemCount
import woowacourse.movie.presentation.activities.movielist.adapter.MovieListAdapter
import woowacourse.movie.presentation.activities.movielist.adapter.type.MovieViewType
import woowacourse.movie.presentation.activities.ticketing.TicketingActivity
import woowacourse.movie.presentation.model.movieitem.Ad
import woowacourse.movie.presentation.model.movieitem.Movie
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {
    private val adInterval = 3

    @get:Rule
    internal val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    /**
     * [Movie] is fake constructor, not real constructor
     */
    private fun Movie(movieTitle: String, runningTime: Int): Movie =
        Movie(
            movieTitle,
            LocalDate.now(), LocalDate.now(),
            runningTime, "",
            R.drawable.img_sample_movie_thumbnail1
        )

    private fun setCustomAdapter(movieSize: Int, adSize: Int) {
        activityRule.scenario.onActivity { activity ->
            val movieRecyclerView = activity.findViewById<RecyclerView>(R.id.rv_movies)

            movieRecyclerView.adapter = MovieListAdapter(
                List(movieSize) { Movie("테스트1", 100) },
                List(adSize) {
                    Ad(R.drawable.img_native_ad_banner, "https://woowacourse.github.io/")
                },
                {},
                {},
            )
        }
    }

    @Test
    internal fun 리사이클러뷰는_정의된만큼_아이템을_갖는다() {
        val movieSize = 6
        val adSize = 2
        val expected = movieSize + adSize

        setCustomAdapter(movieSize, adSize)

        onView(withId(R.id.rv_movies))
            .check(matchItemCount(expected))
    }

    @Test
    internal fun 세_번에_한_번씩_광고가_등장한다() {
        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rv_movies)

            val expected = recyclerView.adapter?.getItemViewType(adInterval)
            val actual = MovieViewType.AD

            assertEquals(expected, actual)
        }
    }

    @Test
    internal fun 세_번에_한_번_외에는_영화_정보가_등장한다() {
        activityRule.scenario.onActivity { activity ->
            val recyclerView = activity.findViewById<RecyclerView>(R.id.rv_movies)

            for (position in 0 until adInterval) {
                val expected = recyclerView.adapter?.getItemViewType(position)
                val actual = MovieViewType.MOVIE

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    internal fun 영화를_클릭하면_티켓팅_화면으로_이동한다() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
            .perform(
                actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickViewWithId(R.id.btn_book))
            )

        intended(hasComponent(TicketingActivity::class.java.name))
    }
}
