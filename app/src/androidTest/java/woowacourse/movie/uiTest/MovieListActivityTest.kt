package woowacourse.movie.uiTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.activity.MovieListActivity

class MovieListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun haveMovieTitle() {
        onView(withId(R.id.movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun haveMovieDate() {
        onView(withId(R.id.movie_date))
            .check(matches(withText("상영일: 2025.4.1")))
    }

    @Test
    fun haveMovieTime() {
        onView(withId(R.id.movie_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun haveMovieButton() {
        onView(withId(R.id.reserve_button))
            .check(matches(withText("지금 예매")))
    }
}
