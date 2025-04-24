package woowacourse.movie.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.MovieFixture
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieTicketActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieActivity::class.java)

    @Test
    fun `초기_화면에는_영화_목록이_있다`() {
        Espresso.onView(ViewMatchers.withId(R.id.movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun 영화_목록_아이템에는_영화_타이틀이_해리포터와_마법사의_돌이다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(MovieFixture.HARRY_POTTER_TITLE)))
    }

    @Test
    fun 영화_목록_아이템에는_영화_상영일이_있다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_date))
            .check(ViewAssertions.matches(ViewMatchers.withText(MovieFixture.HARRY_POTTER_DATE)))
    }

    @Test
    fun 영화_목록_아이템에는_영화_러닝타임이_있다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_running_time))
            .check(ViewAssertions.matches(ViewMatchers.withText(MovieFixture.HARRY_POTTER_RUNNING_TIME)))
    }

    @Test
    fun 영화_목록_아이템에는_영화_포스터가_있다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun 영화_목록_아이템에는_지금예매_버튼이_있다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_book_btn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun 영화_목록_아이템에_버튼이름이_지금예매이다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_book_btn))
            .check(ViewAssertions.matches(ViewMatchers.withText(BOOK_MOVIE)))
    }

    @Test
    fun 영화_목록을_스크롤할_수_있다() {
        Espresso.onView(ViewMatchers.withId(R.id.movies))
            .perform(ViewActions.swipeUp())
    }

    @Test
    fun 한_영화를_클릭하면_예약_페이지로_넘어간다() {
        Espresso.onView(ViewMatchers.withId(R.id.movie_item))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.movie_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(MovieFixture.HARRY_POTTER_TITLE)))
        Espresso.onView(ViewMatchers.withId(R.id.movie_date))
            .check(ViewAssertions.matches(ViewMatchers.withText(MovieFixture.HARRY_POTTER_DATE)))
        Espresso.onView(ViewMatchers.withId(R.id.movie_running_time))
            .check(ViewAssertions.matches(ViewMatchers.withText(MovieFixture.HARRY_POTTER_RUNNING_TIME)))
    }

    companion object {
        private const val BOOK_MOVIE = "지금 예매"
    }
}
