package woowacourse.movie.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MovieMainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieMainActivity::class.java)

    @Test
    fun testMovieListDisplayed() {
        onView(withId(R.id.movieList)).check(matches(isDisplayed()))
        onView(withId(R.id.movieReservation)).perform(click())
    }
}
