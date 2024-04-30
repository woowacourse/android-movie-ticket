package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.presentation.movie_detail.MovieDetailActivity

@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieDetailActivity::class.java)

    @Test
    fun plusButtonTest() {
        onView(withId(R.id.plus_button))
            .check(matches(isDisplayed()))

        onView(withId(R.id.plus_button))
            .perform(click())
        onView(withId(R.id.quantity_text_view)).check(matches(withText("2")))
    }

    @Test
    fun minusButtonTest() {
        onView(withId(R.id.plus_button))
            .perform(click())

        onView(withId(R.id.minus_button))
            .check(matches(isDisplayed()))
            .perform(click())
        onView(withId(R.id.quantity_text_view)).check(matches(withText("1")))
    }

    @Test
    fun confirmButton() {
        onView(withId(R.id.buy_ticket_button))
            .check(matches(isDisplayed()))
            .perform(click())
        onView(withId(R.id.seat_button))
            .check(matches(isDisplayed()))
    }
}
