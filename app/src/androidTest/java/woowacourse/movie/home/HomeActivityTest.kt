package woowacourse.movie.home

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.TestFixture.moviesFirstItem

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    private lateinit var view: HomeContract.View
    private lateinit var presenter: HomeContract.Presenter

    @Before
    fun setUp() {
        view = mockk<HomeContract.View>()
        presenter = HomePresenter(view)
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_타이틀을_보여준다`() {
        val firstMovieTitle = presenter.obtainMovies()[0].title

        moviesFirstItem.onChildView(withId(R.id.item_movie_catalog_text_view_title)).check(
            matches(withText(firstMovieTitle)),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_상영일을_보여준다`() {
        val firstMovieScreeningDate = presenter.obtainMovies()[0].screeningDate

        moviesFirstItem.onChildView(withId(R.id.item_movie_catalog_text_view_screening_date)).check(
            matches(withText(firstMovieScreeningDate)),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_상영시간을_보여준다`() {
        val firstMovieRunningTime = presenter.obtainMovies()[0].runningTime

        moviesFirstItem.onChildView(withId(R.id.item_movie_catalog_text_view_running_time)).check(
            matches(withText(firstMovieRunningTime)),
        )
    }
}
