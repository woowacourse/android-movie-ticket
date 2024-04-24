package woowacourse.movie.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.main.MovieMainActivity

@RunWith(AndroidJUnit4::class)
class MovieMainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieMainActivity::class.java)

    @Test
    fun `영화_목록이_화면에_표시된다`() {
        onView(withId(R.id.mainList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_영화_제목이_표시된다`() {
        onView(withId(R.id.movieTitle))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_영화_상영일이_표시된다`() {
        onView(withId(R.id.movieDate))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `영화_아이템에_영화_러닝타임이_표시된다`() {
        onView(withId(R.id.movieRunningTime))
            .check(matches(isDisplayed()))
    }
}
