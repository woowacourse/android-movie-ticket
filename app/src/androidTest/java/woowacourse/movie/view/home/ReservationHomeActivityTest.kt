package woowacourse.movie.view.home

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.TestFixture.FIRST_ITEM_POSITION
import woowacourse.movie.TestFixture.movies
import woowacourse.movie.TestFixture.moviesFirstItem

@RunWith(AndroidJUnit4::class)
class ReservationHomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ReservationHomeActivity::class.java)

    @Test
    fun `영화_목록에서_첫번째_아이템의_타이틀을_보여준다`() {
        moviesFirstItem.onChildView(withId(R.id.item_movie_catalog_text_view_title)).check(
            matches(withText(movies[FIRST_ITEM_POSITION].title)),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_상영일을_보여준다`() {
        moviesFirstItem.onChildView(withId(R.id.item_movie_catalog_text_view_screening_date)).check(
            matches(withText(movies[FIRST_ITEM_POSITION].screeningDate)),
        )
    }

    @Test
    fun `영화_목록에서_첫번째_아이템의_상영시간을_보여준다`() {
        moviesFirstItem.onChildView(withId(R.id.item_movie_catalog_text_view_running_time)).check(
            matches(withText(movies[FIRST_ITEM_POSITION].runningTime)),
        )
    }
}
