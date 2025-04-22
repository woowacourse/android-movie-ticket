package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.MovieFixture.HARRY_POTTER_DATE
import woowacourse.movie.MovieFixture.HARRY_POTTER_RUNNING_TIME
import woowacourse.movie.MovieFixture.HARRY_POTTER_TITLE

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieTicketActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieActivity::class.java)

    @Test
    fun `초기_화면에는_영화_목록이_있다`() {
        onView(withId(R.id.movies))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 영화_목록_아이템에는_영화_타이틀이_해리포터와_마법사의_돌이다() {
        onView(withId(R.id.movie_title))
            .check(matches(withText(HARRY_POTTER_TITLE)))
    }

    @Test
    fun 영화_목록_아이템에는_영화_상영일이_있다() {
        onView(withId(R.id.movie_date))
            .check(matches(withText(HARRY_POTTER_DATE)))
    }

    @Test
    fun 영화_목록_아이템에는_영화_러닝타임이_있다() {
        onView(withId(R.id.movie_running_time))
            .check(matches(withText(HARRY_POTTER_RUNNING_TIME)))
    }

    @Test
    fun 영화_목록_아이템에는_영화_포스터가_있다() {
        onView(withId(R.id.movie_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 영화_목록_아이템에는_지금예매_버튼이_있다() {
        onView(withId(R.id.movie_book_btn))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 영화_목록_아이템에_버튼이름이_지금예매이다() {
        onView(withId(R.id.movie_book_btn))
            .check(matches(withText(BOOK_MOVIE)))
    }

    @Test
    fun 영화_목록을_스크롤할_수_있다() {
        onView(withId(R.id.movies))
            .perform(swipeUp())
    }

    @Test
    fun 한_영화를_클릭하면_예약_페이지로_넘어간다() {
        onView(withId(R.id.movie_item))
            .perform(click())
        onView(withId(R.id.movie_title))
            .check(matches(withText(HARRY_POTTER_TITLE)))
        onView(withId(R.id.movie_date))
            .check(matches(withText(HARRY_POTTER_DATE)))
        onView(withId(R.id.movie_running_time))
            .check(matches(withText(HARRY_POTTER_RUNNING_TIME)))
    }

    companion object {
        private const val BOOK_MOVIE = "지금 예매"
    }
}
